package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FromCurrencyCode implements NebulaAPIParameter {
    private final String fromCurrency;

    @Override
    public String getKey() {
        return "from_currency";
    }

    @Override
    public String getValue() {
        return fromCurrency;
    }
}
