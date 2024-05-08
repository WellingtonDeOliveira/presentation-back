package br.com.base.authentication.api.controllers.grouproles.v1.annotations;

import br.com.base.shared.annotations.web.*;
import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(
        summary = "Add group roles roles",
        description = "Requires role: " + RoleType.Roles.ROLE_ADD_GROUP_ROLES_ROLES
)
@PreAuthorize("hasAnyRole(T( br.com.base.shared.models.enums.RoleType).ROLE_ADD_GROUP_ROLES_ROLES)")
@RequestMapping(method = RequestMethod.POST, path = "/{groupRoleId}/roles", produces = "application/json")
@OpenApiResponse201
@OpenApiResponse400
@OpenApiResponse401
@OpenApiResponse403
@OpenApiResponse404
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddGroupRolesRolesEndpoint {
}
