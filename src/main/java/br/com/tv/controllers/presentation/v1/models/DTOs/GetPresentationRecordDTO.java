package br.com.tv.controllers.presentation.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Schema(name = "GetPresentationRecordsDTO")
public record GetPresentationRecordDTO(
        @Schema(description = "Record id", example = "00000000-0000-0000-0000-000000000000") UUID id,
        @Schema(description = "Record TvId.", example = "00000000-0000-0000-0000-000000000000") UUID tvId,
        @Schema(description = "Name", example = "file") String name,
        @Schema(description = "Type.", example = ".png") String type,
        @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z") OffsetDateTime createdAt,
        @Schema(description = "Update date.", example = "2000-01-01T00:00:00Z") OffsetDateTime updatedAt,
        @Schema(description = "Delete date.", example = "2000-01-01T00:00:00Z") OffsetDateTime deletedAt,
        @Schema(description = "Files", example = "-") List<byte[]> files) implements Serializable {
}
