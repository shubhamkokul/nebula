package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FromSymbol implements NebulaAPIParameter {
    private final String fromSymbol;

    @Override
    public String getKey() {
        return "from_symbol";
    }

    @Override
    public String getValue() {
        return fromSymbol;
    }
}
