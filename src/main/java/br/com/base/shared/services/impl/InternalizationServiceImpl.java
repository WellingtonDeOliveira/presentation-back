package br.com.base.shared.services.impl;

import br.com.base.shared.services.InternalizationService;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class InternalizationServiceImpl implements InternalizationService {
    private final MessageSource messageSource;

    public InternalizationServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String msgCode) {
        return getMessage(msgCode, "");
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(resolvable, locale);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "not resolvable";
        }
    }

    @Override
    public String getMessage(String msgCode, Object... parameters) {
        try {
            Locale locale = LocaleContextHolder.getLocale();
            return messageSource.getMessage(msgCode, parameters, locale);
        } catch (Exception ex) {
            ex.printStackTrace();
            return msgCode;
        }
    }
}
