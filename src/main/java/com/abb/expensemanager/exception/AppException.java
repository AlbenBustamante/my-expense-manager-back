package com.abb.expensemanager.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The general application exception.
 */
public class AppException extends RuntimeException {

    @Getter
    private final HttpStatus status;

    public AppException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }
    
}
