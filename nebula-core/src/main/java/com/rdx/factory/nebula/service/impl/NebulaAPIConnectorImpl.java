package com.rdx.factory.nebula.service.impl;

import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.service.NebulaAPIConnector;
import com.rdx.factory.nebula.service.NebulaAPIParameter;
import com.rdx.factory.nebula.util.NebulaAPIParameterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NebulaAPIConnectorImpl implements NebulaAPIConnector {

    private final String baseUrl;
    private final String apiKey;
    private final Integer timeOut;

    public NebulaAPIConnectorImpl(String baseUrl, String apiKey, Integer timeOut) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        this.timeOut = timeOut;
    }
    @Override
    public String getRequest(NebulaAPIParameter... apiParameters) throws NebulaRequestException {
        String params = getParameters(apiParameters);
        try {
            URL request = new URL(baseUrl + params);
            URLConnection connection = request.openConnection();
            connection.setConnectTimeout(timeOut);
            connection.setReadTimeout(timeOut);

            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream(), "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStream);
            StringBuilder responseBuilder = new StringBuilder();

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                responseBuilder.append(line);
            }
            bufferedReader.close();
            return responseBuilder.toString();
        } catch (Exception e) {
            throw new NebulaRequestException("failure sending request", e);
        }
    }

    private String getParameters(NebulaAPIParameter... apiParameters) {
        NebulaAPIParameterBuilder urlBuilder = new NebulaAPIParameterBuilder();
        for(NebulaAPIParameter param : apiParameters) {
            urlBuilder.append(param);
        }
        urlBuilder.append("apikey", apiKey);
        return urlBuilder.getUrlBuilder().toString();
    }
}
