package com.rdx.factory.nebula.exception;

public class NebulaRequestException extends RuntimeException {

    public NebulaRequestException(String message, Exception e) {
        super(message, e);
    }

    public NebulaRequestException(String message) {
        super(message);
    }
}
