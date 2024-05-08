package br.com.base.authentication.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import br.com.base.shared.models.enums.RoleType;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_groups_roles_link_roles")
public class GroupRoleLinkRoleEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_role_id", nullable = false)
    private GroupRoleEntity group;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 150, nullable = false)
    private RoleType role;

    public GroupRoleLinkRoleEntity() {
    }

    public GroupRoleLinkRoleEntity(GroupRoleEntity group, RoleType role) {
        this.group = group;
        this.role = role;
    }

    public GroupRoleEntity getGroup() {
        return group;
    }

    public RoleType getRole() {
        return role;
    }
}
