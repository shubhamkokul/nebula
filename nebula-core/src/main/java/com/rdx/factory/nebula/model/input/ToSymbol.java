package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToSymbol implements NebulaAPIParameter {
    private final String toSymbol;

    @Override
    public String getKey() {
        return "to_symbol";
    }

    @Override
    public String getValue() {
        return toSymbol;
    }
}
