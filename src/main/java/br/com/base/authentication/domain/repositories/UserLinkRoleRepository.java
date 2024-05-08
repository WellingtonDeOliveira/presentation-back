package br.com.base.authentication.domain.repositories;

import br.com.base.authentication.domain.models.entities.UserLinkRoleEntity;
import br.com.base.shared.models.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserLinkRoleRepository extends JpaRepository<UserLinkRoleEntity, UUID> {
    void deleteByUserIdAndRoleIn(UUID userId, List<RoleType> roles);
}
