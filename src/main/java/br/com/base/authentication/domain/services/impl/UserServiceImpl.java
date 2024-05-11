package br.com.base.authentication.domain.services.impl;

import br.com.base.authentication.api.controllers.user.v1.models.DTOs.*;
import br.com.base.authentication.domain.models.entities.GroupRoleEntity;
import br.com.base.authentication.domain.models.entities.UserEntity;
import br.com.base.authentication.domain.models.entities.UserLinkGroupRoleEntity;
import br.com.base.authentication.domain.models.entities.UserLinkRoleEntity;
import br.com.base.authentication.domain.repositories.GroupRoleRepository;
import br.com.base.authentication.domain.repositories.UserLinkGroupRoleRepository;
import br.com.base.authentication.domain.repositories.UserLinkRoleRepository;
import br.com.base.authentication.domain.repositories.UserRepository;
import br.com.base.authentication.domain.services.UserService;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.models.enums.RoleType;
import br.com.base.shared.utils.StringUtil;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRoleRepository groupRoleRepository;
    private final UserLinkRoleRepository userLinkRoleRepository;
    private final UserLinkGroupRoleRepository userLinkGroupRoleRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void addRoles(UUID userId, List<RoleType> roles) throws EntityNotFoundException {
        var user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found."));
        roles = removeDuplicateRoles(roles);

        var rolesLinked = user.getRoles().stream().map(UserLinkRoleEntity::getRole).toList();
        roles.removeAll(rolesLinked);
        if (roles.isEmpty()) return;

        var rolesToLink = buildUserLinkRoles(user, roles);
        userLinkRoleRepository.saveAll(rolesToLink);
    }

    @Override
    public void addGroups(UUID userId, Set<UUID> groupsRolesIds) throws EntityNotFoundException {
        checkUserExistOrThrowException(userId);
        var groupsLinked = userLinkGroupRoleRepository.findByUserId(userId);
        groupsLinked.stream().map(g -> g.getGroup().getId()).forEach(groupsRolesIds::remove);
        if (groupsRolesIds.isEmpty()) return;
        var groupsToLink = groupRoleRepository.findByIdIn(groupsRolesIds);
        var user = UserEntity.builder().id(userId).build();
        var links = buildGroupsLinkRoles(user, groupsToLink);
        userLinkGroupRoleRepository.saveAll(links);
    }

    private List<UserLinkGroupRoleEntity> buildGroupsLinkRoles(UserEntity user, List<GroupRoleEntity> groups) {
        return groups.stream().map(group -> new UserLinkGroupRoleEntity(user, group)).toList();
    }

    @Transactional
    @Override
    public void removeRoles(UUID userId, List<RoleType> roles) {
        checkUserExistOrThrowException(userId);
        roles = removeDuplicateRoles(roles);
        if (roles.isEmpty()) return;
        userLinkRoleRepository.deleteByUserIdAndRoleIn(userId, roles);
    }

    @Transactional
    @Override
    public void removeGroups(UUID userId, Set<UUID> groupsRolesIds) throws EntityNotFoundException {
        if (groupsRolesIds != null && groupsRolesIds.isEmpty()) return;
        checkUserExistOrThrowException(userId);
        userLinkGroupRoleRepository.deleteByUserIdAndGroupIdIn(userId, groupsRolesIds);
    }

    @Override
    public void create(CreateUserRequestDTO request) {
        var user = UserEntity.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .campus(request.campus())
                .build();
        userRepository.save(user);
    }

    @Override
    public GetUserResponseDTO search(GetUserRequestDTO request) {
        var pageable = request.buildPageable();

        var page = userRepository.search(request.getSearch(), pageable);

        if (page.isEmpty()) {
            page = userRepository.search(StringUtil.like(request.getSearch()), pageable);
        }
        return parseToUserPageableResultDTO(page);
    }

    private GetUserResponseDTO parseToUserPageableResultDTO(Page<UserEntity> result) {
        List<GetUserRecordsDTO> content = result.getContent().stream()
                .map(user -> GetUserRecordsDTO.builder()
                        .userId(user.getId())
                        .username(user.getUsername())
                        .campus(user.getCampus())
                        .groups(user.getGroupsNames())
                        .deletedAt(user.getDeletedAt())
                        .build()).toList();
        var page = new PageImpl<>(content, result.getPageable(), result.getTotalElements());
        return new GetUserResponseDTO(page);
    }

    @Override
    public void checkUserExistOrThrowException(UUID userId) throws EntityNotFoundException {
        boolean exists = userRepository.existsById(userId);
        if (exists)
            return;
        throw new EntityNotFoundException("User not found.");
    }

    private List<UserLinkRoleEntity> buildUserLinkRoles(UserEntity user, List<RoleType> roles) {
        return roles.stream().map(role -> UserLinkRoleEntity.builder().user(user).role(role).build()).toList();
    }

    private List<RoleType> removeDuplicateRoles(List<RoleType> roles) {
        return roles.stream().distinct().collect(Collectors.toList());
    }

    @Override
    public GetUserRolesResponseDTO getRoles(UUID userId, GetUserRolesRequestDTO request) {
        var pageable = request.buildPageable();
        var page = userLinkRoleRepository.findAll(pageable);
        return parseToPageableResultDTO(page);
    }

    public GetUserRolesResponseDTO parseToPageableResultDTO(Page<UserLinkRoleEntity> pageableResul) {
        List<GetUserRolesRecordsDTO> content = pageableResul.getContent().stream().map(e ->
                GetUserRolesRecordsDTO.builder()
                        .id(e.getId())
                        .createdAt(e.getCreatedAt())
                        .updatedAt(e.getUpdatedAt())
                        .role(e.getRole())
                        .build()
        ).toList();

        var page = new PageImpl<>(content, pageableResul.getPageable(), pageableResul.getTotalElements());

        return new GetUserRolesResponseDTO(page);
    }
}
