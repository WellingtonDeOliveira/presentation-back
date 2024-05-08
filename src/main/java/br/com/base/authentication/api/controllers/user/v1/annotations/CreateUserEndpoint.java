package br.com.base.authentication.api.controllers.user.v1.annotations;

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
        summary = "Create user"
)
@RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
@OpenApiResponse200
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateUserEndpoint {
}
