package br.com.base.authentication.api.controllers.grouproles.v1.models.DTOs;

import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Schema(name = "GetGroupRolesDetailsResponse")
public record GetGroupRolesDetailsResponseDTO(
        @Schema(description = "Records id.", example = "00000000-0000-0000-0000-000000000000")
        UUID id,
        @Schema(description = "Creation date.", example = "2000-01-01T00:00:00Z")
        OffsetDateTime createdAt,
        @Schema(description = "Last update date.", example = "2000-01-01T00:00:00Z")
        OffsetDateTime updatedAt,
        @Schema(description = "Group name.", example = "Management")
        String name,
        @Schema(description = "Group description.", example = "Group management")
        String description,
        @Schema(description = "Rules quantity.", example = "4")
        long rulesQuantity,
        @Schema(description = "List roles.")
        List<RoleType> roles,
        @Schema(description = "Users quantity.", example = "10")
        long usersQuantity,
        @Schema(description = "List users.")
        List<GetGroupRolesDetailsUserDTO> users
) {
    public GetGroupRolesDetailsResponseDTO(Builder builder) {
        this(
                builder.id,
                builder.createdAt,
                builder.updatedAt,
                builder.name,
                builder.description,
                builder.rulesQuantity,
                builder.roles,
                builder.usersQuantity,
                builder.users
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private UUID id;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;
        private String name;
        private String description;
        private long rulesQuantity;
        private List<RoleType> roles;
        private long usersQuantity;
        private List<GetGroupRolesDetailsUserDTO> users;

        public Builder id(UUID id) {
            this.id = id;
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

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder rulesQuantity(long rulesQuantity) {
            this.rulesQuantity = rulesQuantity;
            return this;
        }

        public Builder roles(List<RoleType> roles) {
            this.roles = roles;
            return this;
        }

        public Builder usersQuantity(long usersQuantity) {
            this.usersQuantity = usersQuantity;
            return this;
        }

        public Builder users(List<GetGroupRolesDetailsUserDTO> users) {
            this.users = users;
            return this;
        }

        public GetGroupRolesDetailsResponseDTO build() {
            return new GetGroupRolesDetailsResponseDTO(this);
        }
    }
}
