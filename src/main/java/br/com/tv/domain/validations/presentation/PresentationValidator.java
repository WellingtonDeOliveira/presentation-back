package br.com.tv.domain.validations.presentation;

import br.com.tv.domain.models.entities.FilesEntity;

public interface PresentationValidator {

    void validateForExtensions(String extension);

    void validateForTypePresentation(FilesEntity files);
}
