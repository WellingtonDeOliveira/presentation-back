package br.com.base.authentication.api.controllers.auth.v1.annotations;

import br.com.base.shared.annotations.web.OpenApiResponse200;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Operation(summary = "Login")
@RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json")
@OpenApiResponse200
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginEndpoint {
}
