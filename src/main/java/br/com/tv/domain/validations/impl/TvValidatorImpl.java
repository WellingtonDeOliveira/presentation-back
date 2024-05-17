package br.com.tv.domain.validations.impl;

import br.com.tv.domain.repositories.TvRepository;
import br.com.tv.domain.validations.TvValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@AllArgsConstructor
@Component
public class TvValidatorImpl implements TvValidator {

    private final TvRepository tvRepository;

    @Override
    public boolean thisIsWithoutPresententation(UUID tvId) {
        return tvRepository.thisIsWithoutPresententation(tvId);
    }
}
