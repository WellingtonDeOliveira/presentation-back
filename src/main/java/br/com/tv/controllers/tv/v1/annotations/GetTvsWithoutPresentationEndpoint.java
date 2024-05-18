package br.com.tv.controllers.tv.v1.annotations;

import br.com.base.shared.annotations.web.OpenApiResponse200;
import br.com.base.shared.annotations.web.OpenApiResponse400;
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
        summary = "Get TV without presentation",
        description = "Requires role: " + RoleType.Roles.ROLE_GET_ALL_TVS_WITHOUT_PRESENTATION
)
@PreAuthorize("hasAnyRole(T(br.com.base.shared.models.enums.RoleType).ROLE_GET_ALL_TVS_WITHOUT_PRESENTATION)")
@RequestMapping(method = RequestMethod.GET, path = "/presentation", produces = "application/json")
@OpenApiResponse200
@OpenApiResponse400
@OpenApiResponse401
@OpenApiResponse403
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetTvsWithoutPresentationEndpoint {
}
