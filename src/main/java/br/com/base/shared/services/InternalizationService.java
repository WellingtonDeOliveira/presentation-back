package br.com.base.shared.services;

import org.springframework.context.MessageSourceResolvable;

public interface InternalizationService {
    String getMessage(String msgCode);

    String getMessage(MessageSourceResolvable resolvable);

    String getMessage(String msgCode, Object... parameters);
}
