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
        summary = "Get group roles details",
        description = "Requires role: " + RoleType.Roles.ROLE_GET_GROUP_ROLES_DETAILS
)
@PreAuthorize("hasAnyRole(T(br.com.base.shared.models.enums.RoleType).ROLE_GET_GROUP_ROLES_DETAILS)")
@RequestMapping(method = RequestMethod.GET, path = "/{groupRoleId}", produces = "application/json")
@OpenApiResponse200
@OpenApiResponse400
@OpenApiResponse401
@OpenApiResponse403
@OpenApiResponse404
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetGroupRolesDetailsEndpoint {
}
