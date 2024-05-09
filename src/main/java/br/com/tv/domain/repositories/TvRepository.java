package br.com.tv.domain.repositories;

import br.com.tv.domain.models.entities.TvEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TvRepository extends JpaRepository<TvEntity, UUID> {
}
