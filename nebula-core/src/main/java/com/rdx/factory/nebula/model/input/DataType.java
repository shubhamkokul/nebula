package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DataType implements NebulaAPIParameter {
    CSV("csv"),
    JSON("json");

    private final String dataType;


    @Override
    public String getKey() {
        return "datatype";
    }

    @Override
    public String getValue() {
        return dataType;
    }
}
