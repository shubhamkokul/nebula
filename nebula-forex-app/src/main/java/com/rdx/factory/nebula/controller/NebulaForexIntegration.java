package com.rdx.factory.nebula.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class NebulaForexIntegration {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/forex/rates")
    public String getRates() {
        return "Hello Forex";
    }
}
