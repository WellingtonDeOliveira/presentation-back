package br.com.base.authentication.domain.services;

import br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs.*;
import br.com.base.shared.exceptions.EntityAlreadyExistsException;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.models.enums.RoleType;

import java.util.List;
import java.util.UUID;

public interface GroupRoleService {
    GetGroupsRolesResponseDTO getGroups(GetGroupsRolesRequestDTO request);

    GetGroupRolesDetailsResponseDTO getGroupDetails(UUID groupRoleId) throws EntityNotFoundException;

    void create(CreateGroupRolesRequestDTO request) throws EntityAlreadyExistsException;

    void update(UpdateGroupRolesRequestDTO request) throws EntityNotFoundException, EntityAlreadyExistsException;

    void remove(UUID groupRoleId) throws EntityNotFoundException;

    void addRoles(UUID groupRoleId, List<RoleType> roles) throws EntityNotFoundException;

    void removeRoles(UUID groupRoleId, List<RoleType> roles) throws EntityNotFoundException;
}
