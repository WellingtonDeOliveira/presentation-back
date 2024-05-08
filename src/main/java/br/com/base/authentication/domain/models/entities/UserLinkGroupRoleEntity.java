package br.com.base.authentication.domain.models.entities;

import br.com.base.shared.models.entities.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_users_link_groups_roles")
public class UserLinkGroupRoleEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "group_role_id", nullable = false)
    private GroupRoleEntity group;

    public UserLinkGroupRoleEntity() {
    }

    public UserLinkGroupRoleEntity(UserEntity user, GroupRoleEntity group) {
        this.user = user;
        this.group = group;
    }

    public UserEntity getUser() {
        return user;
    }

    public GroupRoleEntity getGroup() {
        return group;
    }
}
