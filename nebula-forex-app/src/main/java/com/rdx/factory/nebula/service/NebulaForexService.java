package com.rdx.factory.nebula.service;

import com.rdx.factory.nebula.model.output.CurrencyExchangeRateData;

public interface NebulaForexService {
    public CurrencyExchangeRateData exchangeRate(String fromCurrencyCode, String toCurrencyCode);
}
