package br.com.tv.domain.repositories;

import br.com.tv.domain.models.entities.FilesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, UUID> {

    @Query("SELECT distinct u "
            + "FROM FilesEntity u "
            + "WHERE "
            + "    (:search is null or lower(u.name) like :search) "
            + " or (:search is null or lower(u.ref) like :search) "
            + " or (:search is null or lower(u.type) like :search) " )
    Page<FilesEntity> search(@Param("search") String search, Pageable pageable);
}
