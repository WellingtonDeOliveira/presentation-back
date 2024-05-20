package br.com.tv.domain.validations.impl;

import br.com.base.shared.exceptions.BusinessException;
import br.com.tv.domain.models.entities.TvEntity;
import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.validations.TvValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class TvValidatorImpl implements TvValidator {

    private final TvRepository tvRepository;

    @Override
    public void thisIsWithoutPresententation(UUID tvId) {
        if (!tvRepository.thisIsWithoutPresententation(tvId))
            throw new BusinessException("Essa TV já possui apresentação atribuida!");
    }

    @Override
    public void listTvsIsEmpty(List<TvEntity> tvs) {
        if (tvs.isEmpty()) {
            throw new BusinessException("TV não encontrada!");
        }
    }
}
