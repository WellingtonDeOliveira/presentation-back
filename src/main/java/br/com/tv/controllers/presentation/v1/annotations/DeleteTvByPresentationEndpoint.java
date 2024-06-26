package br.com.tv.controllers.presentation.v1.annotations;

import br.com.base.shared.annotations.web.OpenApiResponse200;
import br.com.base.shared.annotations.web.OpenApiResponse401;
import br.com.base.shared.annotations.web.OpenApiResponse403;
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
        summary = "Delete TV by Presentation",
        description = "Requires role: " + RoleType.Roles.ROLE_DELETE_TV_BY_PRESENTATION
)
@PreAuthorize("hasAnyRole(T(br.com.base.shared.models.enums.RoleType).ROLE_DELETE_TV_BY_PRESENTATION)")
@RequestMapping(method = RequestMethod.DELETE, path = "/tv/{presentationId}", produces = "application/json")
@OpenApiResponse200
@OpenApiResponse401
@OpenApiResponse403
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeleteTvByPresentationEndpoint {
}
