package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;

public class ToCurrency implements NebulaAPIParameter {
    private String toCurrency;

    public ToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    @Override
    public String getKey() {
        return "to_currency";
    }

    @Override
    public String getValue() {
        return toCurrency;
    }
}
