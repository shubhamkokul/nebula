package com.rdx.factory.nebula.configuration;

import com.rdx.factory.nebula.service.NebulaAPIConnector;
import com.rdx.factory.nebula.service.impl.NebulaAPIConnectorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:nebula-stock.properties")
public class NebulaStockConfiguration {

    private static final String FOREX_SERVICE_URL="base.url";

    private static final String API_KEY = "api.key";

    private static final Integer TIMEOUT = 3000;

    private final Environment env;


    @Autowired
    public NebulaStockConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    NebulaAPIConnector getNebulaAPIConnector() {
        return new NebulaAPIConnectorImpl(env.getProperty(FOREX_SERVICE_URL), env.getProperty(API_KEY), TIMEOUT);
    }
}
