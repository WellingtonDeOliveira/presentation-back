package br.com.base.authentication.api.controllers.user.v1;

import br.com.base.authentication.api.controllers.user.v1.annotations.*;
import br.com.base.authentication.api.controllers.user.v1.models.DTOs.*;
import br.com.base.authentication.domain.services.UserService;
import br.com.base.shared.annotations.web.ApiController;
import br.com.base.shared.annotations.web.OpenApiController;
import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@OpenApiController(name = "Users Management")
@ApiController(path = "/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetUsersEndpoint
    public ResponseEntity<GetUserResponseDTO> search(@ModelAttribute GetUserRequestDTO request) {
        var response = userService.search(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CreateUserEndpoint
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequestDTO request) {
        userService.create(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @UpdatedPasswordEndpoint
    public ResponseEntity<Void> updatedPassword(@PathVariable("userId") UUID userId, @RequestBody @Valid UpdatedPasswordRequestDTO request) {
        userService.updatedPassword(userId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @AddUserGroupsRolesEndpoint
    public ResponseEntity<Void> addUserGroupsRoles(@PathVariable UUID userId, @Schema(description = "List groups roles ids.", example = "[\"00000000-0000-0000-0000-000000000000\"]") @RequestBody Set<UUID> groupsRolesIds) {
        userService.addGroups(userId, groupsRolesIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RemoveUserGroupsRolesEndpoint
    public ResponseEntity<Void> removeUserGroupsRoles(@PathVariable UUID userId, @Schema(description = "List groups roles ids.", example = "[\"00000000-0000-0000-0000-000000000000\"]") @RequestBody Set<UUID> groupsRolesIds) {
        userService.removeGroups(userId, groupsRolesIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetUserRolesEndpoint
    public ResponseEntity<GetUserRolesResponseDTO> getUserRoles(@PathVariable UUID userId, @ModelAttribute GetUserRolesRequestDTO request) {
        var response = userService.getRoles(userId, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @AddUserRolesEndpoint
    public ResponseEntity<Void> addUserRoles(@PathVariable UUID userId, @RequestBody @Schema(description = "List roles.", example = "[\"WRITE\"]") List<RoleType> roles) {
        userService.addRoles(userId, roles);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RemoveUserRolesEndpoint
    public ResponseEntity<Void> removeUserRoles(@PathVariable UUID userId, @RequestBody @Schema(description = "List roles.", example = "[\"WRITE\"]") List<RoleType> roles) {
        userService.removeRoles(userId, roles);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
