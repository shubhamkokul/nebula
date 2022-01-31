package com.rdx.factory.nebula.exception;

public class NebulaValidationException extends RuntimeException {

    public NebulaValidationException(String message, Exception e) {
        super(message, e);
    }

    public NebulaValidationException(String message) {
        super(message);
    }
}
