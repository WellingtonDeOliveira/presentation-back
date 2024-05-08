package br.com.base.authentication.api.controllers.role.v1.annotations;

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
        summary = "Get all roles",
        description = "Requires role: " + RoleType.Roles.ROLE_READ_ROLE
)
@PreAuthorize("hasAnyRole(T(br.com.base.shared.models.enums.RoleType).ROLE_READ_ROLE)")
@RequestMapping(method = RequestMethod.GET, produces = "application/json")
@OpenApiResponse200
@OpenApiResponse401
@OpenApiResponse403
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetAllRolesEndpoint {
}
