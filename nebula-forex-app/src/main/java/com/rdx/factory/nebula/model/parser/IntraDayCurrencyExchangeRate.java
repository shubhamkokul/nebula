package com.rdx.factory.nebula.model.parser;

import com.rdx.factory.nebula.model.input.Interval;
import com.rdx.factory.nebula.model.response.ForexMetaData;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class IntraDayCurrencyExchangeRate extends ForexResponseParser<ForexTimeSeriesCurrencyExchangeRateData> {
    private final Interval intraDay;

    @Override
    ForexTimeSeriesCurrencyExchangeRateData resolve(Map<String, String> metaData, Map<String, Map<String, String>> fxData) {
        return createForexTimeSeriesCurrencyExchangeRateData(metaData, fxData);
    }

    @Override
    String getForexDataKey() {
        return "Time Series FX (" + intraDay.getValue() + ")";
    }

    @Override
    ForexMetaData createMetaData(Map<String, String> values) {
        return new ForexMetaData.Builder()
                .information(values.get("1. Information"))
                .fromSymbol(values.get("2. From Symbol"))
                .toSymbol(values.get("3. To Symbol"))
                .lastRefreshed(values.get("4. Last Refreshed"))
                .interval(values.get("5. Interval"))
                .outputSize(values.get("6. Output Size"))
                .timeZone(values.get("7. Time Zone"))
                .build();
    }
}