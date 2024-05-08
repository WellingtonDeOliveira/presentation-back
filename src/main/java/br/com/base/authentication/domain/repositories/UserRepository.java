package br.com.base.authentication.domain.repositories;

import br.com.base.authentication.domain.models.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT distinct u "
            + "FROM UserEntity u "
            + "WHERE "
            + "    (:search is null or lower(u.username) like :search) "
            + " or (:search is null or lower(u.campus) like :search) ")
    Page<UserEntity> search(@Param("search") String search, Pageable pageable);

    Optional<UserEntity> findByUsername(String username);

    boolean existsByUsername(String username);
}
