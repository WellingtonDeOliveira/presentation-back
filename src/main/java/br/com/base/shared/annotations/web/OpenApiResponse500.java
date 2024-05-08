package br.com.base.shared.annotations.web;

import br.com.base.shared.models.DTOs.ProblemDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ApiResponse(
        responseCode = "500",
        description = "There was an internal error.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDTO.class))
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApiResponse500 {
}
