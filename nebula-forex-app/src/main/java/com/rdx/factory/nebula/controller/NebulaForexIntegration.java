package com.rdx.factory.nebula.controller;

import com.rdx.factory.nebula.model.ExchangeRate;
import com.rdx.factory.nebula.service.NebulaForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NebulaForexIntegration {

    private final NebulaForexService nebulaForexService;

    @Autowired
    public NebulaForexIntegration(NebulaForexService nebulaForexService) {
        this.nebulaForexService = nebulaForexService;
    }

    @GetMapping(value = "/forex/exchange/rates")
    public String getRates() {
        return nebulaForexService.exchangeRate("USD", "JPY");
    }
}
