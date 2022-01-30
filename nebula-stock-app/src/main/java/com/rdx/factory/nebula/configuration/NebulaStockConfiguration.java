package com.rdx.factory.nebula.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:nebula-stock.properties")
public class NebulaStockConfiguration {
}
