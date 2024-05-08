package br.com.base.authentication.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import br.com.base.shared.models.enums.RoleType;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "tb_users_link_roles")
public class UserLinkRoleEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 150, nullable = false)
    private RoleType role;

    public UserLinkRoleEntity() {
    }

    private UserLinkRoleEntity(Builder builder) {
        super.id = builder.id;
        this.user = builder.user;
        this.role = builder.role;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UserEntity getUser() {
        return user;
    }

    public RoleType getRole() {
        return role;
    }


    public static final class Builder {
        private UUID id;
        private UserEntity user;
        private RoleType role;

        public Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder user(UserEntity user) {
            this.user = user;
            return this;
        }

        public Builder role(RoleType role) {
            this.role = role;
            return this;
        }


        public UserLinkRoleEntity build() {
            return new UserLinkRoleEntity(this);
        }
    }
}
