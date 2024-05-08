package br.com.base.authentication.api.controllers.grouproles.v1;

import br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs.*;
import br.com.base.authentication.domain.services.GroupRoleService;
import br.com.base.authentication.api.controllers.grouproles.v1.annotations.*;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.models.enums.RoleType;
import br.com.base.shared.annotations.web.ApiController;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@OpenApiController(name = "Groups Roles Management")
@ApiController(path = "/v1/groups-roles")
public class GroupRolesController {
    private final GroupRoleService groupRoleService;

    public GroupRolesController(GroupRoleService groupRoleService) {
        this.groupRoleService = groupRoleService;
    }

    @GetGroupsRolesEndpoint
    public ResponseEntity<GetGroupsRolesResponseDTO> getGroupsRoles(@ModelAttribute GetGroupsRolesRequestDTO request) {
        var response = groupRoleService.getGroups(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetGroupRolesDetailsEndpoint
    public ResponseEntity<GetGroupRolesDetailsResponseDTO> getGroupRolesDetails(@PathVariable UUID groupRoleId) {
        var response = groupRoleService.getGroupDetails(groupRoleId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CreateGroupRolesEndpoint
    public ResponseEntity<Void> createGroupRoles(@RequestBody @Valid CreateGroupRolesRequestDTO request) {
        groupRoleService.create(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @UpdateGroupRolesEndpoint
    public ResponseEntity<Void> updateGroupRoles(
            @PathVariable UUID groupRoleId,
            @RequestBody @Valid UpdateGroupRolesRequestDTO request
    ) {
        if (groupRoleId.equals(request.groupRoleId())) {
            groupRoleService.update(request);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new BusinessException("Resource identifier is not compatible.");
        }
    }

    @RemoveGroupRolesEndpoint
    public ResponseEntity<Void> removeGroupRoles(@PathVariable UUID groupRoleId) {
        groupRoleService.remove(groupRoleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @AddGroupRolesRolesEndpoint
    public ResponseEntity<Void> addGroupRolesRoles(
            @PathVariable UUID groupRoleId,
            @RequestBody
            @Schema(description = "List roles.", example = "[\"WRITE\"]")
            List<RoleType> roles
    ) {
        groupRoleService.addRoles(groupRoleId, roles);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RemoveGroupRolesRolesEndpoint
    public ResponseEntity<Void> removeGroupRolesRoles(
            @PathVariable UUID groupRoleId,
            @RequestBody
            @Schema(description = "List roles.", example = "[\"WRITE\"]")
            List<RoleType> roles
    ) {
        groupRoleService.removeRoles(groupRoleId, roles);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
