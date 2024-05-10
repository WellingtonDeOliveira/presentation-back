package br.com.tv.controllers.presentation.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "UpdateNamePresentationRequestDTO")
public record UpdateNamePresentationRequestDTO (
        @Schema(description = "Name Presentation", example = "Apresentação") @NotBlank String name ) {
}
