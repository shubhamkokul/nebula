package com.rdx.factory.nebula.model.parser;

import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.input.Interval;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import lombok.Getter;

@Getter
public class ForexTimeSeriesCurrencyExchangeRate {

    private final ForexTimeSeriesCurrencyExchangeRateData forexTimeSeriesCurrencyExchangeRateData;

    private ForexTimeSeriesCurrencyExchangeRate(ForexTimeSeriesCurrencyExchangeRateData forexTimeSeriesCurrencyExchangeRateData) {
        this.forexTimeSeriesCurrencyExchangeRateData = forexTimeSeriesCurrencyExchangeRateData;
    }

    public static ForexTimeSeriesCurrencyExchangeRate from(Interval interval, String json) throws NebulaRequestException {
        IntraDayCurrencyExchangeRate parser = new IntraDayCurrencyExchangeRate(interval);
        return new ForexTimeSeriesCurrencyExchangeRate(parser.parseJson(json));
    }

    public static ForexTimeSeriesCurrencyExchangeRate from(String interval, String json) throws NebulaRequestException {
        DailyCurrencyExchangeRate parser = new DailyCurrencyExchangeRate(interval);
        return new ForexTimeSeriesCurrencyExchangeRate(parser.parseJson(json));
    }
}
