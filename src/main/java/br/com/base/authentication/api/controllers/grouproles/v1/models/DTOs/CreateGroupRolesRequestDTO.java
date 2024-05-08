package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Schema(name = "CreateGroupRolesRequest")
public record CreateGroupRolesRequestDTO(
        @Schema(description = "Group name.", example = "Management")
        @NotEmpty
        @NotBlank
        @Length(min = 1, max = 100)
        String name,
        @Schema(description = "Group description.", example = "Group management")
        @Length(max = 200)
        String description,
        @Schema(description = "List roles.", example = "[\"WRITE\",\"READ\",\"EDIT\"]")
        List<RoleType> roles
) {
}
