package com.rdx.factory.nebula.model.parser;

import com.rdx.factory.nebula.model.response.ForexMetaData;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class ForexDailyCurrencyExchangeRate extends ForexResponseParser<ForexTimeSeriesCurrencyExchangeRateData> {
    private final String daily;

    @Override
    ForexTimeSeriesCurrencyExchangeRateData resolve(Map<String, String> metaData, Map<String, Map<String, String>> fxData) {
        return createForexTimeSeriesCurrencyExchangeRateData(metaData, fxData);
    }

    @Override
    String getForexDataKey() {
        return "Time Series FX (" + daily + ")";
    }

    @Override
    ForexMetaData createMetaData(Map<String, String> values) {
        return new ForexMetaData.Builder()
                .information(values.get("1. Information"))
                .fromSymbol(values.get("2. From Symbol"))
                .toSymbol(values.get("3. To Symbol"))
                .outputSize(values.get("4. Output Size"))
                .lastRefreshed(values.get("5. Last Refreshed"))
                .timeZone(values.get("6. Time Zone"))
                .build();
    }
}