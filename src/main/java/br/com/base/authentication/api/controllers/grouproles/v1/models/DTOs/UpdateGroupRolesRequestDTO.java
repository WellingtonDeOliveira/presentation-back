package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

@Schema(name = "UpdateGroupRolesRequest")
public record UpdateGroupRolesRequestDTO(
        @Schema(description = "Group role id.", example = "00000000-0000-0000-0000-000000000000")
        @NotNull
        UUID groupRoleId,
        @Schema(description = "Group name.", example = "Management")
        @NotEmpty
        @NotBlank
        @Length(min = 1, max = 100)
        String name,
        @Schema(description = "Group description.", example = "Group management")
        @Length(max = 200)
        String description
) {
}
