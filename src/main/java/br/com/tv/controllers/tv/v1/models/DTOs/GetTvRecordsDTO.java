package br.com.tv.controllers.tv.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
@Schema(name = "GetTvRecordsDTO")
public record GetTvRecordsDTO(
    @Schema(description = "Record id", example = "00000000-0000-0000-0000-000000000000") UUID id,
    @Schema(description = "Campus.", example = "Fortaleza") String campus,
    @Schema(description = "User.", example = "User") UserAssociatedByTvRecordDTO user,
    @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z") OffsetDateTime createdAt,
    @Schema(description = "Name", example = "tv-fortaleza-01") String name) {
}
