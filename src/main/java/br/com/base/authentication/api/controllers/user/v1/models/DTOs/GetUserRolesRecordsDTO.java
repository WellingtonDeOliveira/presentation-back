package br.com.base.authentication.api.controllers.user.v1.models.DTOs;

import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.UUID;

@Schema(name = "GetUserRolesRecords")
public record GetUserRolesRecordsDTO(
        @Schema(description = "Records id.", example = "00000000-0000-0000-0000-000000000000")
        UUID id,
        @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z")
        OffsetDateTime createdAt,
        @Schema(description = "Last update date.", example = "2000-01-01T00:00:00Z")
        OffsetDateTime updatedAt,
        @Schema(description = "Role.")
        RoleType role,
        @Schema(description = "Campus.")
        String campus
) {
    public GetUserRolesRecordsDTO(Builder builder) {
        this(builder.id, builder.createdAt, builder.updatedAt, builder.role, builder.campus);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;
        private RoleType role;
        private String campus;

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder campus(String campus) {
            this.campus = campus;
            return this;
        }

        public Builder createdAt(OffsetDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder updatedAt(OffsetDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder role(RoleType role) {
            this.role = role;
            return this;
        }

        public GetUserRolesRecordsDTO build() {
            return new GetUserRolesRecordsDTO(this);
        }
    }
}
