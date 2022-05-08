package com.rdx.factory.nebula.configuration;

import com.plaid.client.ApiClient;
import com.plaid.client.request.PlaidApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

@Configuration
@PropertySource("classpath:nebula-plaid.properties")
public class PlaidConfiguration {

    private static final String PLAID_CLIENT_ID = "plaid.client.id";

    private static final String PLAID_SECRET_KEY = "plaid.secret.key";

    private static final String PLAID_PUBLIC_KEY = "plaid.public.key";

    private static final String PLAID_ENV = "plaid.env";

    private static final Integer TIMEOUT = 3000;

    private final Environment env;

    @Autowired
    public PlaidConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    PlaidApi getPlaidClient() {
        Map<String, String> apiKeys = new HashMap<>();
        apiKeys.put("clientId", env.getProperty(PLAID_CLIENT_ID));
        apiKeys.put("secret", env.getProperty(PLAID_SECRET_KEY));
        ApiClient apiClient = new ApiClient(apiKeys);
        if (env.getProperty(PLAID_ENV).equalsIgnoreCase("Sandbox"))
            apiClient.setPlaidAdapter(ApiClient.Sandbox);
        else
            apiClient.setPlaidAdapter(ApiClient.Development);
        return apiClient.createService(PlaidApi.class);
    }

    @Bean("publicKey")
    String getPublicKey() {
        return env.getProperty(PLAID_PUBLIC_KEY);
    }

    @Bean("clientId")
    String getClientId() {
        return env.getProperty(PLAID_CLIENT_ID);
    }

    @Bean("secretKey")
    String getSecretKey() {
        return env.getProperty(PLAID_SECRET_KEY);
    }

}
