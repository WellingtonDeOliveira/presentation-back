package br.com.tv.controllers.files.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Schema(name = "GetFilesRecordsDTO")
public record GetFilesRecordsDTO(
    @Schema(description = "Record id", example = "00000000-0000-0000-0000-000000000000") UUID id,
    @Schema(description = "Type.", example = ".png") String type,
    @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z") OffsetDateTime createdAt,
    @Schema(description = "Name", example = "arquivo2") String name) {
}
