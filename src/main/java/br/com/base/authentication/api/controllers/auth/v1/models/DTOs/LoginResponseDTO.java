package br.com.base.authentication.api.controllers.auth.v1.models.DTOs;

import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.Set;

@Builder
@Schema(name = "LoginResponse")
public record LoginResponseDTO(
        @Schema(description = "Token", example = "ekJhbGciZiJIUzI1niJ4")
        String token,
        @Schema(description = "Nickname", example = "Nickname")
        String nickname,
        @Schema(description = "Name", example = "Name")
        String name,
        @Schema(description = "Campus", example = "Fortaleza")
        String campus,
        @Schema(description = "Roles", example = "[\"WRITE\"]")
        Set<RoleType> roles,
        @Schema(description = "Groups", example = "[\"Management\"]")
        Set<String> groups
) implements Serializable {}
