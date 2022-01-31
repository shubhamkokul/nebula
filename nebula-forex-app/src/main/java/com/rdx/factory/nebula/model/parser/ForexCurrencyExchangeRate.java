package com.rdx.factory.nebula.model.parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.model.response.ForexCurrencyExchangeRateData;
import com.rdx.factory.nebula.service.NebulaResponseParser;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Getter
public class ForexCurrencyExchangeRate {

    private final ForexCurrencyExchangeRateData forexCurrencyExchangeRateData;

    private ForexCurrencyExchangeRate(ForexCurrencyExchangeRateData forexCurrencyExchangeRateData) {
        this.forexCurrencyExchangeRateData = forexCurrencyExchangeRateData;
    }

    public static ForexCurrencyExchangeRate from(String json) throws NebulaRequestException {
        CurrencyExchangeRateParser parser = new CurrencyExchangeRateParser();
        return parser.parseJson(json);
    }

    private static class CurrencyExchangeRateParser extends NebulaResponseParser<ForexCurrencyExchangeRate> {
        public ForexCurrencyExchangeRate resolve(JsonObject rootObject) {
            Type dataType = new TypeToken<Map<String, Map<String, String>>>() {
            }.getType();
            try {
                Map<String, Map<String, String>> data = GSON.fromJson(rootObject, dataType);
                ForexCurrencyExchangeRateData exchangeData = createCurrencyExchangeRateData(data.values().stream()
                        .findFirst()
                        .orElse(Collections.emptyMap()));
                return new ForexCurrencyExchangeRate(exchangeData);
            } catch (JsonSyntaxException e) {
                throw new NebulaRequestException("currency exchange api change", e);
            }
        }

        private ForexCurrencyExchangeRateData createCurrencyExchangeRateData(Map<String, String> values) throws JsonSyntaxException {
            return new ForexCurrencyExchangeRateData.Builder()
                    .fromCurrencyCode(values.get("1. From_Currency Code"))
                    .fromCurrencyName(values.get("2. From_Currency Name"))
                    .toCurrencyCode(values.get("3. To_Currency Code"))
                    .toCurrencyName(values.get("4. To_Currency Name"))
                    .exchangeRate(new BigDecimal(values.get("5. Exchange Rate")))
                    .lastRefreshed(values.get("6. Last Refreshed"))
                    .timeZone(values.get("7. Time Zone"))
                    .latestBidPrice(new BigDecimal(values.get("8. Bid Price")))
                    .latestAskPrice(new BigDecimal(values.get("9. Ask Price")))
                    .statusCode(HttpStatus.OK)
                    .build();
        }

    }
}
