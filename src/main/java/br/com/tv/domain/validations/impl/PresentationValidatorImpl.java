package br.com.tv.domain.validations.impl;

import br.com.base.shared.exceptions.BusinessException;
import br.com.base.shared.models.enums.AllowedExtension;
import br.com.tv.domain.models.entities.FilesEntity;
import br.com.tv.domain.models.entities.PresentationEntity;
import br.com.tv.domain.services.PresentationService;
import br.com.tv.domain.validations.PresentationValidator;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import static br.com.base.shared.models.enums.AllowedExtension.*;

@AllArgsConstructor
@Component
public class PresentationValidatorImpl implements PresentationValidator {

    private static final Logger logger = LoggerFactory.getLogger(PresentationService.class);

    @Override
    public void validateForExtensions(String extension) {
        if (extension != null && !isAllowedExtension(extension)) {
            throw new BusinessException("Extensão de arquivo não permitida");
        }
    }

    @Override
    public void validateForTypePresentation(String extension, PresentationEntity presentation) {
        logger.info("file: "+AllowedExtension.getTypeByExtension(extension));
        logger.info("presentation: "+presentation.getType());
        if (!presentation.getType().equals(AllowedExtension.getTypeByExtension(extension)))
            throw  new BusinessException("O tipo de arquivo não corresponde com o tipo de apresentação");
    }

}
