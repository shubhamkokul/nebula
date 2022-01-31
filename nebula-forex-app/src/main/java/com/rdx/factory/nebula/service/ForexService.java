package com.rdx.factory.nebula.service;

import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.ForexIntraDayCurrencyExchangeRateData;

public interface ForexService {
    public ForexCurrencyExchangeRateData exchangeRate(String fromCurrencyCode, String toCurrencyCode);

    public ForexIntraDayCurrencyExchangeRateData IntraDayMoves(String fromCurrencyCode, String toCurrencyCode, String interval, String outputSize, String dataType);
}
