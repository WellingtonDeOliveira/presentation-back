package br.com.base.shared.annotations.web;

import br.com.base.shared.models.DTOs.ProblemDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.*;

@ApiResponse(
        responseCode = "404",
        description = "Resource not found.",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDTO.class))
)
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpenApiResponse404 {
}
