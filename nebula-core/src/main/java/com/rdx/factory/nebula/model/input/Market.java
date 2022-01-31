package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Market implements NebulaAPIParameter {
    private final String market;


    @Override
    public String getKey() {
        return "market";
    }

    @Override
    public String getValue() {
        return market;
    }
}
