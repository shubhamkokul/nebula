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

    private static final Integer TIMEOUT = 3000;

    private final Environment env;


    @Autowired
    public NebulaStockConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    NebulaAPIConnector getNebulaAPIConnector() {
        return new NebulaAPIConnectorImpl("", "", TIMEOUT);
    }
}
