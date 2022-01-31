package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Symbol implements NebulaAPIParameter {
    private final String symbol;

    @Override
    public String getKey() {
        return "symbol";
    }

    @Override
    public String getValue() {
        return symbol;
    }
}
