package com.rdx.factory.nebula.model.response;

import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class NebulaResponse implements Serializable {
    public HttpStatus statusCode;
    public String errorMessage;
}
