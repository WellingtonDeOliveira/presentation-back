package br.com.base.shared.exceptions;

import java.io.Serial;

@SuppressWarnings("unused")
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
