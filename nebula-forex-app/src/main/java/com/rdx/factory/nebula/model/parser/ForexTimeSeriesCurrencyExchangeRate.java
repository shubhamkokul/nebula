package com.rdx.factory.nebula.model.parser;

import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import com.rdx.factory.nebula.service.NebulaResponseParser;
import lombok.Getter;

@Getter
public class ForexTimeSeriesCurrencyExchangeRate {

    private final ForexTimeSeriesCurrencyExchangeRateData forexTimeSeriesCurrencyExchangeRateData;

    private ForexTimeSeriesCurrencyExchangeRate(ForexTimeSeriesCurrencyExchangeRateData forexTimeSeriesCurrencyExchangeRateData) {
        this.forexTimeSeriesCurrencyExchangeRateData = forexTimeSeriesCurrencyExchangeRateData;
    }

    public static ForexTimeSeriesCurrencyExchangeRate from(String json, NebulaResponseParser<ForexTimeSeriesCurrencyExchangeRateData> parser) throws NebulaRequestException {
        return new ForexTimeSeriesCurrencyExchangeRate(parser.parseJson(json));
    }
}
