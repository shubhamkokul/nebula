package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;

public class FromCurrency implements NebulaAPIParameter {
    private final String fromCurrency;

    public FromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    @Override
    public String getKey() {
        return "from_currency";
    }

    @Override
    public String getValue() {
        return fromCurrency;
    }
}
