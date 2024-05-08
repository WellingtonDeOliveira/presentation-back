package br.com.base.configurations;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageSourceConfiguration {
    @Bean
    public MessageSource messageSource() {
        final var messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setDefaultEncoding("ISO-8859-1");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }
}
