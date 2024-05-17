package br.com.tv.domain.validations;

import java.util.UUID;

public interface TvValidator {

    boolean thisIsWithoutPresententation(UUID tvId);
}
