package br.com.tv.domain.repositories;


import br.com.tv.domain.models.entities.PresentationLinkTvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PresentationLinkTvRepository extends JpaRepository<PresentationLinkTvEntity, UUID> {

    @Query("SELECT presentation.id " +
            "FROM PresentationLinkTvEntity p " +
            "WHERE tv.id = :id")
    UUID findPresentationIdByTvId(UUID id);

    void deleteAllByPresentationId(UUID id);
}
