package com.rdx.factory.nebula.service;

import com.rdx.factory.nebula.model.ExchangeRate;
import org.springframework.http.ResponseEntity;

public interface NebulaForexService {

     enum Function {
        CURRENCY_EXCHANGE_RATE;
    }

    public String exchangeRate(String fromCurrencyCode, String toCurrencyCode);
}
