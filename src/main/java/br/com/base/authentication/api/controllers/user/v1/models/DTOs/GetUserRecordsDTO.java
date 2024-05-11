package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
@Schema(name = "GetUserRecords")
public record GetUserRecordsDTO(
        @Schema(description = "Records id.", example = "00000000-0000-0000-0000-000000000000") UUID userId,
        @Schema(description = "Username.", example = "User") String username,
        @Schema(description = "Campus.", example = "Fortaleza") String campus,
        @Schema(description = "DeletedAt.", example = "OffsetDateTime") OffsetDateTime deletedAt,
        @Schema(description = "Groups.", example = "Management")  Set<String> groups) {
}
