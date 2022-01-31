package com.rdx.factory.nebula.model.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.service.NebulaResponseParser;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class ForexResponseParser<Data> extends NebulaResponseParser<Data> {

    abstract Data resolve(Map<String, String> metaData,
                          Map<String, Map<String, String>> fxData);

    abstract String getForexDataKey();

    @Override
    public Data resolve(JsonObject rootObject)  {
        Type metaDataType = new TypeToken<Map<String, String>>() {
        }.getType();
        Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
        }.getType();
        try {
            Map<String, String> metaData = GSON.fromJson(rootObject.get("Meta Data"), metaDataType);
            Map<String, Map<String, String>> fxData = GSON.fromJson(rootObject.get(getForexDataKey()), dataType);
            return resolve(metaData, fxData);
        } catch (JsonSyntaxException e) {
            throw new NebulaRequestException("Unable to parse Forex IntraDay Currency Rate timeSeries", e);
        }
    }
}
