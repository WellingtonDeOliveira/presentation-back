package br.com.tv.domain.validations.presentation;

import br.com.base.shared.exceptions.BusinessException;
import br.com.tv.domain.models.entities.FilesEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import static br.com.base.shared.models.enums.AllowedExtension.*;

@AllArgsConstructor
@Component
public class PresentationValidatorImpl implements PresentationValidator{

    @Override
    public void validateForExtensions(String extension) {
        if (extension != null && !isAllowedExtension(extension)) {
            throw new BusinessException("Extensão de arquivo não permitida");
        }
    }

    @Override
    public void validateForTypePresentation(FilesEntity file) {

    }

}
