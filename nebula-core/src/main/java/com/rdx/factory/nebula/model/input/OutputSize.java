package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum OutputSize implements NebulaAPIParameter {
    COMPACT("compact"),
    FULL("full");

    private final String outputSize;

    @Override
    public String getKey() {
        return "outputsize";
    }

    @Override
    public String getValue() {
        return outputSize;
    }
}
