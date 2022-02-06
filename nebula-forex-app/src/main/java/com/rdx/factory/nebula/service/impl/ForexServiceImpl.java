package com.rdx.factory.nebula.service.impl;

import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.input.*;
import com.rdx.factory.nebula.model.parser.ForexCurrencyExchangeRate;
import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.parser.ForexTimeSeriesCurrencyExchangeRate;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import com.rdx.factory.nebula.service.NebulaAPIConnector;
import com.rdx.factory.nebula.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.rdx.factory.nebula.util.ForexRequestUtil.*;


@Service
public class ForexServiceImpl implements ForexService {

    private final NebulaAPIConnector nebulaAPIConnector;

    @Autowired
    public ForexServiceImpl(NebulaAPIConnector nebulaAPIConnector) {
        this.nebulaAPIConnector = nebulaAPIConnector;
    }

    @Override
    public ForexCurrencyExchangeRateData exchangeRate(String fromCurrencyCode, String toCurrencyCode) {
        try {
            String json = nebulaAPIConnector.getRequest(Function.CURRENCY_EXCHANGE_RATE, new FromCurrencyCode(fromCurrencyCode), new ToCurrencyCode(toCurrencyCode));
            return ForexCurrencyExchangeRate.from(json).getForexCurrencyExchangeRateData();
        } catch (NebulaRequestException e) {
            return new ForexCurrencyExchangeRateData.Builder().statusCode(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build();
        }
    }

    @Override
    public ForexTimeSeriesCurrencyExchangeRateData intraDayMoves(String fromCurrencyCode, String toCurrencyCode, String interval, String outputSize, String dataType) {
        try {
            String json = nebulaAPIConnector.getRequest(Function.FX_INTRADAY,
                    new FromSymbol(fromCurrencyCode),
                    new ToSymbol(toCurrencyCode),
                    getDaily(interval),
                    getOutputSize(outputSize),
                    getDataType(dataType));
            return ForexTimeSeriesCurrencyExchangeRate.from(getDaily(interval), json).getForexTimeSeriesCurrencyExchangeRateData();
        } catch (NebulaRequestException e) {
            return new ForexTimeSeriesCurrencyExchangeRateData.Builder().statusCode(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build();
        }
    }

    @Override
    public ForexTimeSeriesCurrencyExchangeRateData dailyMoves(String fromCurrencyCode, String toCurrencyCode, String outputSize, String dataType) {
        try {
            String json = nebulaAPIConnector.getRequest(Function.FX_DAILY,
                    new FromSymbol(fromCurrencyCode),
                    new ToSymbol(toCurrencyCode),
                    getOutputSize(outputSize),
                    getDataType(dataType));
            return ForexTimeSeriesCurrencyExchangeRate.from(getDaily(), json).getForexTimeSeriesCurrencyExchangeRateData();
        } catch(NebulaRequestException e) {
            return new ForexTimeSeriesCurrencyExchangeRateData.Builder().statusCode(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build();
        }
    }

    @Override
    public ForexTimeSeriesCurrencyExchangeRateData weeklyMoves(String fromCurrencyCode, String toCurrencyCode, String dataType) {
        try {
            String json = nebulaAPIConnector.getRequest(Function.FX_DAILY,
                    new FromSymbol(fromCurrencyCode),
                    new ToSymbol(toCurrencyCode),
                    getDataType(dataType));
            return ForexTimeSeriesCurrencyExchangeRate.from(getWeekly(), json).getForexTimeSeriesCurrencyExchangeRateData();
        } catch(NebulaRequestException e) {
            return new ForexTimeSeriesCurrencyExchangeRateData.Builder().statusCode(HttpStatus.BAD_REQUEST).errorMessage(e.getMessage()).build();
        }
    }
}
