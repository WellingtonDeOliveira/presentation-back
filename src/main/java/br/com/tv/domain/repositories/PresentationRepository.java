package br.com.tv.domain.repositories;

import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PresentationRepository extends JpaRepository<PresentationEntity, UUID> {

    @Query("SELECT distinct p "
            + "FROM PresentationEntity p "
            + "INNER JOIN p.tv t "
            + "WHERE "
            + "    (:search is null or lower(p.name) like :search) "
            + " or (:search is null or lower(t.campus) like :search) " )
    Page<PresentationEntity> search(@Param("search") String search, Pageable pageable);

    List<PresentationEntity> findAllByDeletedAtBefore(OffsetDateTime currentDateTime);
}
