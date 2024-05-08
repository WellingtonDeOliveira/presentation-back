package br.com.base.authentication.domain.constraints;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.passay.*;
import org.passay.spring.SpringMessageResolver;
import org.springframework.context.MessageSource;

import java.util.Arrays;
import java.util.List;

public class ValidPasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {
    private final MessageSource messageSource;

    public ValidPasswordConstraintValidator(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void initialize(ValidPassword arg0) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        PasswordValidator validator = buildPasswordValidatorRules();
        RuleResult result = validator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        } else {
            List<String> messages = validator.getMessages(result);
            messages.forEach(m ->
                    context.buildConstraintViolationWithTemplate(m)
                            .addConstraintViolation()
            );
            context.disableDefaultConstraintViolation();
            return false;
        }
    }

    private PasswordValidator buildPasswordValidatorRules() {
        return new PasswordValidator(getMessageResolver(), getRules());
    }

    private List<Rule> getRules() {
        return Arrays.asList(
                // at least 8 characters
                new LengthRule(8, 30),
                // at least one upper-case character
                new CharacterRule(EnglishCharacterData.UpperCase, 1),
                // at least one lower-case character
                new CharacterRule(EnglishCharacterData.LowerCase, 1),
                // at least one digit character
                new CharacterRule(EnglishCharacterData.Digit, 1),
                // at least one symbol (special character)
                new CharacterRule(EnglishCharacterData.Special, 1),
                // no whitespace
                new WhitespaceRule()
        );
    }

    private MessageResolver getMessageResolver() {
        return new SpringMessageResolver(messageSource);
    }
}
