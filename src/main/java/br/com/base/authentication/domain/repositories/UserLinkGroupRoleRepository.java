package br.com.base.authentication.domain.repositories;

import br.com.base.authentication.domain.models.entities.UserLinkGroupRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface UserLinkGroupRoleRepository extends JpaRepository<UserLinkGroupRoleEntity, UUID> {
    boolean existsByGroupId(UUID groupRoleId);

    long countByGroupId(UUID groupRoleId);

    List<UserLinkGroupRoleEntity> findByUserId(UUID userId);
    void deleteByUserIdAndGroupIdIn(UUID userId, Set<UUID> groupsRolesIds);
}
