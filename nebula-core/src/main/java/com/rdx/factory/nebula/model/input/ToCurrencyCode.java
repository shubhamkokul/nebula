package com.rdx.factory.nebula.model.input;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ToCurrencyCode implements NebulaAPIParameter {
    private final String toCurrency;

    @Override
    public String getKey() {
        return "to_currency";
    }

    @Override
    public String getValue() {
        return toCurrency;
    }
}
