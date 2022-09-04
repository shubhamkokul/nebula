package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Interval implements NebulaAPIParameter {

    ONE("1min"),
    FIVE("5min"),
    FIFTEEN("15min"),
    THIRTY("30min"),
    SIXTY("60min");

    private final String interval;

    @Override
    public String getKey() {
        return "interval";
    }

    @Override
    public String getValue() {
        return interval;
    }
}
