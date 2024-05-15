package br.com.tv.controllers.tv.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(name = "UserAssociatedByTvRecordDTO")
public record UserAssociatedByTvRecordDTO(
        @Schema(description = "userId", example = "00000000-0000-0000-0000-000000000000") UUID id,
        @Schema(description = "User Name", example = "Name") String name
) {
}
