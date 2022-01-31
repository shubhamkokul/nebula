package com.rdx.factory.nebula.service;

import com.google.gson.*;
import com.rdx.factory.nebula.exception.NebulaRequestException;

import java.time.format.DateTimeFormatter;

public abstract class NebulaResponseParser<Data> {
    protected final DateTimeFormatter SIMPLE_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected final DateTimeFormatter DATE_WITH_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    protected final DateTimeFormatter DATE_WITH_SIMPLE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    protected final String SIMPLE_DATE_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d";
    protected final String DATE_WITH_SIMPLE_TIME_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d\\s+[0-2]\\d:[0-5]\\d:[0-5]\\d";
    protected final String DATE_WITH_TIME_PATTERN = "^\\d{4}-[01]\\d-[0-3]\\d\\s+[0-2]\\d:[0-5]\\d:[0-5]\\d";

    private static final JsonParser PARSER = new JsonParser();

    protected static Gson GSON = new Gson();

    public abstract Data resolve(JsonObject rootObject);

    public Data parseJson(String json) {
        try {
            JsonElement jsonElement = PARSER.parse(json);
            JsonObject rootObject = jsonElement.getAsJsonObject();

            JsonElement errorMessage = rootObject.get("Error Message");
            if (errorMessage != null) {
                throw new NebulaRequestException(errorMessage.getAsString());
            }
            return resolve(rootObject);
        }  catch (JsonSyntaxException e) {
            throw new NebulaRequestException("error parsing json", e);
        }
    }
}
