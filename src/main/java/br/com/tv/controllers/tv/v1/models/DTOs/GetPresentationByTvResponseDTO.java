package br.com.tv.controllers.tv.v1.models.DTOs;

import br.com.tv.controllers.files.v1.models.DTOs.GetFileResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Schema(name = "GetPresentationByTvResponseDTO")
public record GetPresentationByTvResponseDTO (
        @Schema(description = "Record id", example = "00000000-0000-0000-0000-000000000000") UUID id,
        @Schema(description = "Name", example = "Apresentação") String name,
        @Schema(description = "Type.", example = "imagem/video") String type,
        @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z") OffsetDateTime createdAt,
        @Schema(description = "Files", example = "-") List<GetFileResponseDTO> files) {
}
