package br.com.base.authentication.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import br.com.base.shared.models.enums.RoleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.time.OffsetDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_users")
public class UserEntity extends BaseEntity implements UserDetails {
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;
    @Column(name = "password", nullable = false, length = 200)
    private String password;
    @Column(name = "campus", nullable = false, length = 20)
    private String campus;
    @Column(name = "deleted_at", nullable = false)
    protected OffsetDateTime deletedAt;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserLinkRoleEntity> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserLinkGroupRoleEntity> groups;

    private UserEntity(Builder builder) {
        super.id = builder.id;
        this.username = builder.username;
        this.campus = builder.campus;
        this.password = builder.password;
        this.roles = builder.roles;
        this.groups = builder.groups;
        this.deletedAt = builder.deletedAt;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAllRoles().stream().map(r -> new SimpleGrantedAuthority(r.toString())).toList();
    }

    public Set<RoleType> getIndividualRoles() {
        return roles.stream().map(UserLinkRoleEntity::getRole).collect(Collectors.toUnmodifiableSet());
    }

    public Set<RoleType> getRolesInGroups() {
        return groups.stream()
                .flatMap(g -> g.getGroup().getRoles().stream().map(GroupRoleLinkRoleEntity::getRole))
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<RoleType> getAllRoles() {
        return Stream.of(getIndividualRoles(), getRolesInGroups()).flatMap(Collection::stream)
                .collect(Collectors.toUnmodifiableSet());
    }

    public Set<UserLinkRoleEntity> getRoles() {
        return roles;
    }

    public Set<UserLinkGroupRoleEntity> getGroups() {
        return groups;
    }

    public Set<String> getGroupsNames() {
        return groups.stream().map(g -> g.getGroup().getName()).collect(Collectors.toUnmodifiableSet());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static final class Builder {
        private UUID id;
        private String username;
        private String password;
        private OffsetDateTime deletedAt;
        private String campus;
        private Set<UserLinkRoleEntity> roles;
        private Set<UserLinkGroupRoleEntity> groups;

        public Builder() {
        }

        public Builder id(UUID id) {
            this.id = id;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder campus(String campus) {
            this.campus = campus;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder deletedAt(OffsetDateTime deletedAt) {
            this.deletedAt = deletedAt;
            return this;
        }

        public Builder roles(Set<UserLinkRoleEntity> roles) {
            this.roles = roles;
            return this;
        }

        public Builder groups(Set<UserLinkGroupRoleEntity> groups) {
            this.groups = groups;
            return this;
        }

        public UserEntity build() {
            return new UserEntity(this);
        }
    }
}
