package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.authentication.domain.constraints.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

@Schema(name = "CreateUserRequest")
public record CreateUserRequestDTO(
        @Schema(description = "Username.", example = "username")
        @NotEmpty
        @NotBlank
        @Length(min = 3, max = 50)
        String username,
        @Schema(description = "Name.", example = "Name")
        @NotEmpty
        @NotBlank
        @Length(min = 3, max = 50)
        String name,
        @Schema(description = "Password.", example = "password")
        @ValidPassword
        String password,
        @Schema(description = "Department.", example = "Fortaleza")
        @NotEmpty
        @NotBlank
        @Length(min = 3, max = 20)
        String department
) {
}
