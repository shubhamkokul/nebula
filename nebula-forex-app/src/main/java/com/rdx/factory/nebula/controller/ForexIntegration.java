package com.rdx.factory.nebula.controller;

import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.model.response.ForexIntraDayCurrencyExchangeRateData;
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
    public ForexIntraDayCurrencyExchangeRateData getIntraDayRates(@PathVariable String fromCurrency,
                                                                  @PathVariable String toCurrency,
                                                                  @PathVariable String interval,
                                                                  @PathVariable String outputSize,
                                                                  @PathVariable String dataType) {
        return nebulaForexService.IntraDayMoves(fromCurrency.toUpperCase(Locale.ROOT), toCurrency.toUpperCase(Locale.ROOT), interval, outputSize, dataType);
    }
}
