package br.com.base.authentication.domain.services;

import br.com.base.authentication.domain.models.entities.UserLinkRoleEntity;

import java.util.List;

public interface RoleService {
    void addRoleUser(List<UserLinkRoleEntity> roles);

    List<UserLinkRoleEntity> getLinkedUsers();
}
