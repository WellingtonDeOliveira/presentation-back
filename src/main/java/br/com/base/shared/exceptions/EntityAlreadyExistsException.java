package br.com.base.shared.exceptions;

import java.io.Serial;

@SuppressWarnings("unused")
public class EntityAlreadyExistsException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException() {
        super("Entity already exists.");
    }

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
