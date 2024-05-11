package br.com.tv.controllers.files.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Schema(name = "GetFileResponseDTO")
public record GetFileResponseDTO(
        @Schema(description = "Record id", example = "00000000-0000-0000-0000-000000000000") UUID id,
        @Schema(description = "Name", example = "arquivo2") String name,
        @Schema(description = "Name Apresentation", example = "Apresentação") String namePresentation,
        @Schema(description = "Type.", example = ".png") String type,
        @Schema(description = "Reference name.", example = "00000000-0000-0000-0000-000000000000") String ref,
        @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z") OffsetDateTime createdAt,
        @Schema(description = "File", example = "-") byte[] file) implements Serializable {
}
