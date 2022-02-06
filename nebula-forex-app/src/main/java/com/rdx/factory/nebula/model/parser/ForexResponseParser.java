package com.rdx.factory.nebula.model.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.response.ForexMetaData;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.NebulaTimeSeries;
import com.rdx.factory.nebula.service.NebulaResponseParser;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class ForexResponseParser<Data> extends NebulaResponseParser<Data> {

    abstract Data resolve(Map<String, String> metaData,
                          Map<String, Map<String, String>> fxData);

    abstract String getForexDataKey();

    abstract ForexMetaData createMetaData(Map<String, String> values);

    @Override
    public Data resolve(JsonObject rootObject)  {
        Type metaDataType = new TypeToken<Map<String, String>>() {
        }.getType();
        Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        try {
            Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
            Map<String, Map<String, String>> fxData = GSON.fromJson(rootObject.get(getForexDataKey()), dataType);
            return resolve(metaData, fxData);
        } catch (JsonSyntaxException e) {
            throw new NebulaRequestException("Unable to parse Forex IntraDay Currency Rate timeSeries", e);
        }
    }

    ForexTimeSeriesCurrencyExchangeRateData createForexTimeSeriesCurrencyExchangeRateData(Map<String, String> metaData, Map<String, Map<String, String>> fxData) throws JsonSyntaxException {
        return new ForexTimeSeriesCurrencyExchangeRateData.Builder().forexMetaData(createMetaData(metaData))
                .fxTimeSeries(createTimeSeriesData(fxData))
                .build();
    }

    private List<NebulaTimeSeries> createTimeSeriesData(Map<String, Map<String, String>> fxData) {
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
        return fxTimeSeries;
    }
}
