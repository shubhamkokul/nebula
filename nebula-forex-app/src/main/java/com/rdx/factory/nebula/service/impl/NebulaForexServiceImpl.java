package com.rdx.factory.nebula.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdx.factory.nebula.model.ExchangeRate;
import com.rdx.factory.nebula.service.NebulaForexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Objects;

@Service
public class NebulaForexServiceImpl implements NebulaForexService {

    private static final String FOREX_SERVICE_URL="forex.service.url";

    private static final String API_KEY = "forex.api.key";

    private final RestTemplate restTemplate;

    private final Environment environment;

    private final ObjectMapper objectMapper;

    @Autowired
    public NebulaForexServiceImpl(RestTemplate restTemplate, Environment environment, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.environment = environment;
        this.objectMapper = objectMapper;
        System.out.println(environment.getProperty(API_KEY));
    }
    @Override
    public String exchangeRate(String fromCurrencyCode, String toCurrencyCode) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        String uri = UriComponentsBuilder.fromHttpUrl(Objects.requireNonNull(environment.getProperty(FOREX_SERVICE_URL)))
                .queryParam("function", Function.CURRENCY_EXCHANGE_RATE)
                .queryParam("from_currency", fromCurrencyCode)
                .queryParam("to_currency", toCurrencyCode)
                .queryParam("apikey", environment.getProperty(API_KEY))
                .encode()
                .toUriString();
        return restTemplate.exchange(
                uri,
                HttpMethod.GET,
                entity,
                String.class).getBody();
    }
}
