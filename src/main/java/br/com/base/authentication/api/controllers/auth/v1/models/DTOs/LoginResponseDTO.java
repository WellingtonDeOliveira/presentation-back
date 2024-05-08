package br.com.base.authentication.api.controllers.auth.v1.models.DTOs;

import br.com.base.shared.models.enums.RoleType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Set;

@Schema(name = "LoginResponse")
public record LoginResponseDTO(
        @Schema(description = "Token", example = "ekJhbGciZiJIUzI1niJ4")
        String token,
        @Schema(description = "Nickname", example = "Nickname")
        String nickname,
        @Schema(description = "Campus", example = "Fortaleza")
        String campus,
        @Schema(description = "Roles", example = "[\"WRITE\"]")
        Set<RoleType> roles,
        @Schema(description = "Groups", example = "[\"Management\"]")
        Set<String> groups
) implements Serializable {

    public LoginResponseDTO(Builder builder) {
        this(builder.token, builder.nickname, builder.campus, builder.roles, builder.groups);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private String nickname;
        private String campus;
        private Set<RoleType> roles;
        private Set<String> groups;

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder campus(String campus) {
            this.campus = campus;
            return this;
        }

        public Builder roles(Set<RoleType> roles) {
            this.roles = roles;
            return this;
        }

        public Builder groups(Set<String> groups) {
            this.groups = groups;
            return this;
        }

        public LoginResponseDTO build() {
            return new LoginResponseDTO(this);
        }
    }
}
