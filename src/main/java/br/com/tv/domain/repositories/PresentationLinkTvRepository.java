package br.com.tv.domain.repositories;


import br.com.tv.domain.models.entities.PresentationLinkTvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Repository
public interface PresentationLinkTvRepository extends JpaRepository<PresentationLinkTvEntity, UUID> {

    @Query("SELECT presentation.id " +
            "FROM PresentationLinkTvEntity p " +
            "WHERE tv.id = :id")
    UUID findPresentationIdByTvId(UUID id);

    void deleteAllByPresentationId(UUID id);

    @Modifying()
    @Query("DELETE FROM PresentationLinkTvEntity pltv WHERE pltv.presentation.id = :presentationId AND pltv.tv.id = :tvId")
    void deleteByPresentationIdAndTvId(@Param("presentationId") UUID presentationId, @Param("tvId") UUID tvId);
}
