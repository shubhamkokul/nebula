package com.rdx.factory.nebula.service;

import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;

public interface ForexService {
    ForexCurrencyExchangeRateData exchangeRate(String fromCurrencyCode, String toCurrencyCode);

    ForexTimeSeriesCurrencyExchangeRateData intraDayMoves(String fromCurrencyCode, String toCurrencyCode, String interval, String outputSize, String dataType);

    ForexTimeSeriesCurrencyExchangeRateData dailyMoves(String fromCurrencyCode, String toCurrencyCode, String outputSize, String dataType);

    ForexTimeSeriesCurrencyExchangeRateData weeklyMoves(String fromCurrencyCode, String toCurrencyCode, String dataType);
}
