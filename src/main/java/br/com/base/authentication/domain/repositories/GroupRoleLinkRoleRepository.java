package br.com.base.authentication.domain.repositories;

import br.com.base.authentication.domain.models.entities.GroupRoleLinkRoleEntity;
import br.com.base.shared.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRoleLinkRoleRepository extends JpaRepository<GroupRoleLinkRoleEntity, UUID> {
    void deleteByGroupIdAndRoleIn(UUID groupRoleId, List<RoleType> roles);

    void deleteByGroupId(UUID groupRoleId);

    long countByGroupId(UUID groupRoleId);
}
