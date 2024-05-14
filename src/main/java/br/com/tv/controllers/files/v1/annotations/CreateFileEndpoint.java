package br.com.tv.controllers.files.v1.annotations;

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
        summary = "Create Files",
        description = "Requires role: " + RoleType.Roles.ROLE_CREATED_FILE
)
@PreAuthorize("hasAnyRole(T(br.com.base.shared.models.enums.RoleType).ROLE_CREATED_FILE)")
@RequestMapping(method = RequestMethod.PUT, path = "/{idPresentation}", produces = "application/json")
@OpenApiResponse200
@OpenApiResponse401
@OpenApiResponse403
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateFileEndpoint {
}
