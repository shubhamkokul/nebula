package com.rdx.factory.nebula.model.parser;

import com.google.gson.JsonSyntaxException;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.response.NebulaTimeSeries;
import com.rdx.factory.nebula.model.response.ForexIntraDayCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.ForexMetaData;
import com.rdx.factory.nebula.model.input.Interval;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class ForexIntraDayCurrencyExchangeRate {

    private final ForexIntraDayCurrencyExchangeRateData intraDayCurrencyExchangeRateData;

    private ForexIntraDayCurrencyExchangeRate(ForexIntraDayCurrencyExchangeRateData intraDayCurrencyExchangeRateData) {
        this.intraDayCurrencyExchangeRateData = intraDayCurrencyExchangeRateData;
    }

    public static ForexIntraDayCurrencyExchangeRate from(Interval interval, String json) throws NebulaRequestException {
        IntraDayCurrencyExchangeRate parser = new IntraDayCurrencyExchangeRate(interval);
        return parser.parseJson(json);
    }

    @AllArgsConstructor
    private static class IntraDayCurrencyExchangeRate extends ForexResponseParser<ForexIntraDayCurrencyExchangeRate> {
        private final Interval interval;

        @Override
        ForexIntraDayCurrencyExchangeRate resolve(Map<String, String> metaData, Map<String, Map<String, String>> fxData) {
            List<NebulaTimeSeries> fxTimeSeries = new ArrayList<>();
            try {
                fxData.forEach((key, values) -> fxTimeSeries.add(new NebulaTimeSeries.Builder()
                        .timeStamp(key)
                        .open(new BigDecimal(values.get("1. open")))
                        .high(new BigDecimal(values.get("2. high")))
                        .low(new BigDecimal(values.get("3. low")))
                        .close(new BigDecimal(values.get("4. close")))
                        .build()));
            } catch (Exception e) {
                throw new NebulaRequestException("Unable to parse FX TimeSeries Data");
            }
            return new ForexIntraDayCurrencyExchangeRate(createForexIntraDayCurrencyExchangeRateData(metaData, fxTimeSeries));
        }

        @Override
        String getForexDataKey() {
            return "Time Series FX (" + interval.getValue() + ")";
        }

        private ForexIntraDayCurrencyExchangeRateData createForexIntraDayCurrencyExchangeRateData(Map<String, String> values, List<NebulaTimeSeries> fxTimeSeries) throws JsonSyntaxException {
            return new ForexIntraDayCurrencyExchangeRateData.Builder().forexMetaData(
                            new ForexMetaData.Builder()
                                    .information(values.get("1. Information"))
                                    .fromSymbol(values.get("2. From Symbol"))
                                    .toSymbol(values.get("3. To Symbol"))
                                    .lastRefreshed(values.get("4. Last Refreshed"))
                                    .interval(values.get("5. Interval"))
                                    .outputSize(values.get("6. Output Size"))
                                    .timeZone(values.get("7. Time Zone"))
                                    .build())
                    .fxTimeSeries(fxTimeSeries)
                    .build();
        }
    }
}
