package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "GetGroupRolesDetailsUser")
public record GetGroupRolesDetailsUserDTO(
        @Schema(description = "User id.", example = "00000000-0000-0000-0000-000000000000")
        UUID id,
        @Schema(description = "Username.")
        String username
) {
}
