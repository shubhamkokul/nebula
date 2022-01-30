package com.rdx.factory.nebula.bind;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdx.factory.nebula.service.NebulaAPIConnector;
import com.rdx.factory.nebula.service.impl.NebulaAPIConnectorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Configuration
public class NebulaModule {

    @Bean
    RestTemplate getRestClient() {
        RestTemplate restClient = new RestTemplate(
                new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        restClient.setInterceptors(List.of((request, body, execution) -> {
            return execution.execute(request, body);
        }));
        return restClient;
    }

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

}
