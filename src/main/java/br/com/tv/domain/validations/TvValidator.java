package br.com.tv.domain.validations;

import br.com.tv.domain.models.entities.TvEntity;

import java.util.List;
import java.util.UUID;

public interface TvValidator {

    void thisIsWithoutPresententation(UUID tvId);

    void listTvsIsEmpty(List<TvEntity> tvs);
}
