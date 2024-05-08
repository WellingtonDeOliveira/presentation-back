package br.com.base.authentication.domain.services;

import br.com.base.authentication.api.controllers.user.v1.models.DTOs.CreateUserRequestDTO;
import br.com.base.authentication.api.controllers.user.v1.models.DTOs.GetUserRolesRequestDTO;
import br.com.base.authentication.api.controllers.user.v1.models.DTOs.GetUserRolesResponseDTO;
import br.com.base.shared.exceptions.EntityNotFoundException;
import br.com.base.shared.models.enums.RoleType;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface UserService {

    void create(CreateUserRequestDTO request);

    void checkUserExistOrThrowException(UUID userId) throws EntityNotFoundException;

    void addRoles(UUID userId, List<RoleType> roles) throws EntityNotFoundException;

    void addGroups(UUID userId, Set<UUID> groupsRolesIds) throws EntityNotFoundException;

    void removeRoles(UUID userId, List<RoleType> roles) throws EntityNotFoundException;

    void removeGroups(UUID userId, Set<UUID> groupsRolesIds) throws EntityNotFoundException;

    GetUserRolesResponseDTO getRoles(UUID userId, GetUserRolesRequestDTO request);
}
