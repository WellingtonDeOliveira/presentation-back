package br.com.tv.domain.repositories;

import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.TvEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TvRepository extends JpaRepository<TvEntity, UUID> {

    @Query("SELECT distinct tv "
            + "FROM TvEntity tv "
            + "WHERE "
            + "    (:search is null or lower(tv.name) like :search) "
            + " or (:search is null or lower(tv.campus) like :search) ")
    Page<TvEntity> search(@Param("search") String search, Pageable pageable);

    List<TvEntity> findAllById(List<UUID>[] tvsId);
}
