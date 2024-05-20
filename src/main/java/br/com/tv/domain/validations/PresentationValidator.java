package br.com.tv.domain.validations;

import br.com.base.shared.models.enums.AllowedExtension;
import br.com.tv.domain.models.entities.PresentationEntity;

public interface PresentationValidator {

    void validateForExtensions(String extension);

    void validateForTypePresentation(String extension, PresentationEntity presentation);

//    void validatedPresentationAndLinked
}
