package br.com.base.authentication.api.controllers.auth.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

@Schema(name = "LoginRequest")
public record LoginRequestDTO(
        @Schema(description = "Username.", example = "username")
        @NotEmpty
        String username,
        @Schema(description = "Password.", example = "password")
        @NotEmpty
        String password
) implements Serializable {
}
