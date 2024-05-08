package br.com.base.authentication.domain.services.impl;

import br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs.*;
import br.com.base.authentication.domain.models.entities.GroupRoleLinkRoleEntity;
import br.com.base.authentication.domain.repositories.UserLinkGroupRoleRepository;
import br.com.base.authentication.domain.models.entities.GroupRoleEntity;
import br.com.base.authentication.domain.services.GroupRoleService;
import br.com.base.authentication.domain.repositories.GroupRoleLinkRoleRepository;
import br.com.base.authentication.domain.repositories.GroupRoleRepository;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.exceptions.EntityAlreadyExistsException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.models.enums.RoleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class GroupRoleServiceImpl implements GroupRoleService {
    private final GroupRoleRepository groupRoleRepository;
    private final GroupRoleLinkRoleRepository groupRoleLinkRoleRepository;
    private final UserLinkGroupRoleRepository userLinkGroupRoleRepository;

    public GroupRoleServiceImpl(
            GroupRoleRepository groupRoleRepository,
            GroupRoleLinkRoleRepository groupRoleLinkRoleRepository,
            UserLinkGroupRoleRepository userLinkGroupRoleRepository
    ) {
        this.groupRoleRepository = groupRoleRepository;
        this.groupRoleLinkRoleRepository = groupRoleLinkRoleRepository;
        this.userLinkGroupRoleRepository = userLinkGroupRoleRepository;
    }

    @Override
    public GetGroupsRolesResponseDTO getGroups(GetGroupsRolesRequestDTO request) {
        var pageable = request.buildPageable();
        var page = groupRoleRepository.findAll(pageable);
        return parseToPageableResultDTO(page);
    }

    @Override
    public GetGroupRolesDetailsResponseDTO getGroupDetails(UUID groupRoleId) throws EntityNotFoundException {
        var group = groupRoleRepository.findById(groupRoleId).orElseThrow(() ->
                new EntityNotFoundException("Group roles not found."));
        return GetGroupRolesDetailsResponseDTO.builder()
                .id(group.getId())
                .createdAt(group.getCreatedAt())
                .updatedAt(group.getUpdatedAt())
                .name(group.getName())
                .description(group.getDescription())
                .rulesQuantity(group.getRoles().size())
                .roles(group.getRoles().stream().map(GroupRoleLinkRoleEntity::getRole).toList())
                .usersQuantity(group.getUsers().size())
                .users(group.getUsers().stream().map(u ->
                                new GetGroupRolesDetailsUserDTO(u.getUser().getId(), u.getUser().getUsername())
                        ).toList()
                )
                .build();
    }

    private GetGroupsRolesResponseDTO parseToPageableResultDTO(Page<GroupRoleEntity> pageableResul) {
        List<GetGroupsRolesRecordsDTO> content = pageableResul.getContent().stream().map(e ->
                GetGroupsRolesRecordsDTO.builder()
                        .id(e.getId())
                        .createdAt(e.getCreatedAt())
                        .updatedAt(e.getUpdatedAt())
                        .name(e.getName())
                        .rulesQuantity(groupRoleLinkRoleRepository.countByGroupId(e.getId()))
                        .usersQuantity(userLinkGroupRoleRepository.countByGroupId(e.getId()))
                        .build()
        ).toList();
        var page = new PageImpl<>(content, pageableResul.getPageable(), pageableResul.getTotalElements());
        return new GetGroupsRolesResponseDTO(page);
    }

    @Transactional
    @Override
    public void create(CreateGroupRolesRequestDTO request) {
        boolean exists = groupRoleRepository.existsByName(request.name());
        if (exists) throw new EntityAlreadyExistsException("Group already exists.");
        var group = buildGroupRoleEntity(request);
        groupRoleRepository.save(group);
        addRoles(group, request.roles());
    }

    private GroupRoleEntity buildGroupRoleEntity(CreateGroupRolesRequestDTO request) {
        return GroupRoleEntity.builder()
                .name(request.name())
                .description(request.description())
                .build();
    }

    @Override
    public void update(UpdateGroupRolesRequestDTO request) {
        var group = groupRoleRepository.findById(request.groupRoleId())
                .orElseThrow(() -> new EntityNotFoundException("Group roles not found."));
        var groupO = groupRoleRepository.findByName(request.name());
        if (groupO.isPresent() && !group.getId().equals(groupO.get().getId())) {
            throw new EntityAlreadyExistsException("Group already exists.");
        }
        group = group.toBuilder().name(request.name()).description(request.description()).build();
        groupRoleRepository.save(group);
    }

    @Transactional
    @Override
    public void remove(UUID groupRoleId) throws EntityNotFoundException {
        checkGroupRolesExistOrThrowException(groupRoleId);
        boolean existLinkedUsers = userLinkGroupRoleRepository.existsByGroupId(groupRoleId);
        if (existLinkedUsers)
            throw new BusinessException("Unable to remove group, there are users linked to the group.");
        groupRoleLinkRoleRepository.deleteByGroupId(groupRoleId);
        groupRoleRepository.deleteById(groupRoleId);
    }

    @Override
    public void addRoles(UUID groupRoleId, List<RoleType> roles) {
        var group = groupRoleRepository.findById(groupRoleId).orElseThrow(() ->
                new EntityNotFoundException("Group roles not found."));
        roles = removeDuplicateRoles(roles);
        var rolesLinked = group.getRoles().stream().map(GroupRoleLinkRoleEntity::getRole).toList();
        roles.removeAll(rolesLinked);
        if (roles.isEmpty()) return;
        var rolesToLink = buildGroupRoleLinkRoles(group, roles);
        groupRoleLinkRoleRepository.saveAll(rolesToLink);
    }

    @Transactional
    @Override
    public void removeRoles(UUID groupRoleId, List<RoleType> roles) throws EntityNotFoundException {
        checkGroupRolesExistOrThrowException(groupRoleId);
        roles = removeDuplicateRoles(roles);
        if (roles.isEmpty()) return;
        groupRoleLinkRoleRepository.deleteByGroupIdAndRoleIn(groupRoleId, roles);
    }

    private List<GroupRoleLinkRoleEntity> buildGroupRoleLinkRoles(GroupRoleEntity group, List<RoleType> roles) {
        return roles.stream().map(role -> new GroupRoleLinkRoleEntity(group, role)).toList();
    }

    public void checkGroupRolesExistOrThrowException(UUID groupRoleId) throws EntityNotFoundException {
        boolean exists = groupRoleRepository.existsById(groupRoleId);
        if (exists)
            return;
        throw new EntityNotFoundException("Group roles not found.");
    }

    private void addRoles(GroupRoleEntity group, List<RoleType> roles) {
        if (roles != null) {
            roles = removeDuplicateRoles(roles);
            if (roles.isEmpty()) return;
            var newRoles = roles.stream().map(role ->
                    new GroupRoleLinkRoleEntity(group, role)
            ).toList();
            groupRoleLinkRoleRepository.saveAll(newRoles);
        }
    }

    private List<RoleType> removeDuplicateRoles(List<RoleType> roles) {
        return roles.stream().distinct().collect(Collectors.toList());
    }
}
