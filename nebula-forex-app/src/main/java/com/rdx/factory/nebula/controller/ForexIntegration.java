package com.rdx.factory.nebula.controller;

import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.ForexTimeSeriesCurrencyExchangeRateData;
import com.rdx.factory.nebula.service.ForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class ForexIntegration {

    private final ForexService nebulaForexService;

    @Autowired
    public ForexIntegration(ForexService nebulaForexService) {
        this.nebulaForexService = nebulaForexService;
    }

    @GetMapping(value = "/forex/exchange/rates/{fromCurrency}/to/{toCurrency}")
    public ForexCurrencyExchangeRateData getRates(@PathVariable String fromCurrency, @PathVariable String toCurrency) {
        return nebulaForexService.exchangeRate(fromCurrency.toUpperCase(Locale.ROOT), toCurrency.toUpperCase(Locale.ROOT));
    }

    @GetMapping(value = "/forex/exchange/rates/intraday/{fromCurrency}/to/{toCurrency}/time/{interval}/size/{outputSize}/datatype/{dataType}")
    public ForexTimeSeriesCurrencyExchangeRateData getIntraDayRates(@PathVariable String fromCurrency,
                                                                    @PathVariable String toCurrency,
                                                                    @PathVariable String interval,
                                                                    @PathVariable String outputSize,
                                                                    @PathVariable String dataType) {
        return nebulaForexService.intraDayMoves(fromCurrency.toUpperCase(Locale.ROOT), toCurrency.toUpperCase(Locale.ROOT), interval, outputSize, dataType);
    }

    @GetMapping(value = "/forex/exchange/rates/daily/{fromCurrency}/to/{toCurrency}/size/{outputSize}/datatype/{dataType}")
    public ForexTimeSeriesCurrencyExchangeRateData getDailyRates(@PathVariable String fromCurrency,
                                                                 @PathVariable String toCurrency,
                                                                 @PathVariable String outputSize,
                                                                 @PathVariable String dataType) {
        return nebulaForexService.dailyMoves(fromCurrency.toUpperCase(Locale.ROOT), toCurrency.toUpperCase(Locale.ROOT), outputSize, dataType);
    }

    @GetMapping(value = "/forex/exchange/rates/weekly/{fromCurrency}/to/{toCurrency}/datatype/{dataType}")
    public ForexTimeSeriesCurrencyExchangeRateData getDailyRates(@PathVariable String fromCurrency,
                                                                 @PathVariable String toCurrency,
                                                                 @PathVariable String dataType) {
        return nebulaForexService.weeklyMoves(fromCurrency.toUpperCase(Locale.ROOT), toCurrency.toUpperCase(Locale.ROOT), dataType);
    }
}
