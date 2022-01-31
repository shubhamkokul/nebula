package com.rdx.factory.nebula.service.impl;

import com.rdx.factory.nebula.model.input.FromCurrency;
import com.rdx.factory.nebula.model.input.ToCurrency;
import com.rdx.factory.nebula.model.output.CurrencyExchangeRateData;
import com.rdx.factory.nebula.model.output.CurrencyExchangeRate;
import com.rdx.factory.nebula.service.NebulaAPIConnector;
import com.rdx.factory.nebula.service.NebulaForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class NebulaForexServiceImpl implements NebulaForexService {

    private final NebulaAPIConnector nebulaAPIConnector;

    @Autowired
    public NebulaForexServiceImpl(NebulaAPIConnector nebulaAPIConnector) {
        this.nebulaAPIConnector = nebulaAPIConnector;
    }
    @Override
    public CurrencyExchangeRateData exchangeRate(String fromCurrencyCode, String toCurrencyCode) {
        String json = nebulaAPIConnector.getRequest(Function.CURRENCY_EXCHANGE_RATE, new FromCurrency(fromCurrencyCode), new ToCurrency(toCurrencyCode));
        return CurrencyExchangeRate.from(json).getCurrencyExchangeRateData();
    }
}
