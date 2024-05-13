package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.authentication.domain.constraints.ValidPassword;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "UpdatedPasswordRequestDTO")
public record UpdatedPasswordRequestDTO(
        @Schema(description = "First Password.", example = "password") @ValidPassword String passwordOne,
        @Schema(description = "Second Password.", example = "password") String passwordTwo) {
}
