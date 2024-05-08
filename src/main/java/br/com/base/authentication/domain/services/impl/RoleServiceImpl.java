package br.com.base.authentication.domain.services.impl;

import br.com.base.authentication.domain.models.entities.UserLinkRoleEntity;
import br.com.base.authentication.domain.services.RoleService;
import br.com.base.authentication.domain.repositories.UserLinkRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final UserLinkRoleRepository userLinkRoleRepository;

    public RoleServiceImpl(UserLinkRoleRepository userLinkRoleRepository) {
        this.userLinkRoleRepository = userLinkRoleRepository;
    }

    public void addRoleUser(List<UserLinkRoleEntity> roles) {
        var a = userLinkRoleRepository.saveAll(roles);
    }

    @Override
    public List<UserLinkRoleEntity> getLinkedUsers() {
       return userLinkRoleRepository.findAll();
    }
}
