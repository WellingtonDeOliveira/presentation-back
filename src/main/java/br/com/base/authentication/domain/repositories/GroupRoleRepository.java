package br.com.base.authentication.domain.repositories;

import br.com.base.authentication.domain.models.entities.GroupRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface GroupRoleRepository extends JpaRepository<GroupRoleEntity, UUID> {
    boolean existsByName(String name);

    Optional<GroupRoleEntity> findByName(String name);

    List<GroupRoleEntity> findByIdIn(Set<UUID> userId);
}
