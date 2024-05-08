package br.com.base.authentication.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_groups_roles")
public class GroupRoleEntity extends BaseEntity {

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<GroupRoleLinkRoleEntity> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
    private Set<UserLinkGroupRoleEntity> users;

    public GroupRoleEntity() {
    }

    public GroupRoleEntity(Builder builder) {
        super.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<GroupRoleLinkRoleEntity> getRoles() {
        return roles;
    }

    public Set<UserLinkGroupRoleEntity> getUsers() {
        return users;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Builder toBuilder() {
        return new Builder()
                .id(id)
                .name(name)
                .description(description)
                .createdAt(createdAt)
                .updatedAt(updatedAt);
    }

    public static final class Builder {
        private UUID id;
        private OffsetDateTime createdAt;
        private OffsetDateTime updatedAt;
        private String name;
        private String description;

        public Builder() {
        }

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

        public GroupRoleEntity build() {
            return new GroupRoleEntity(this);
        }
    }
}
