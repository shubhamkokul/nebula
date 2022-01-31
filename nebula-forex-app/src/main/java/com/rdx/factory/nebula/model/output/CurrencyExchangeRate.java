package com.rdx.factory.nebula.model.output;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.rdx.factory.nebula.exception.NebulaRequestException;
import com.rdx.factory.nebula.service.ResponseParser;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;

@Getter @AllArgsConstructor
public class CurrencyExchangeRate {

    private final CurrencyExchangeRateData currencyExchangeRateData;

    public static CurrencyExchangeRate from(String json) {
        CurrencyExchangeRateParser parser = new CurrencyExchangeRateParser();
        return parser.parseJson(json);
    }

    private static class CurrencyExchangeRateParser extends ResponseParser<CurrencyExchangeRate> {
        public CurrencyExchangeRate resolve(JsonObject rootObject) {
            Type dataType = new TypeToken<Map<String, Map<String, String>>>() {}.getType();
            try {
                Map<String, Map<String, String>> data = GSON.fromJson(rootObject, dataType);
                CurrencyExchangeRateData exchangeData = createCurrencyExchangeRateData(data.values().stream()
                        .findFirst()
                        .orElse(Collections.emptyMap()));
                return new CurrencyExchangeRate(exchangeData);
            } catch (JsonSyntaxException e) {
                throw new NebulaRequestException("currency exchange api change", e);
            }
        }

        private CurrencyExchangeRateData createCurrencyExchangeRateData(Map<String, String> values) throws JsonSyntaxException {
            return new CurrencyExchangeRateData(
                    values.get("1. From_Currency Code"),
                    values.get("2. From_Currency Name"),
                    values.get("3. To_Currency Code"),
                    values.get("4. To_Currency Name"),
                    new BigDecimal(values.get("5. Exchange Rate")),
                    values.get("6. Last Refreshed"),
                    values.get("7. Time Zone"),
                    new BigDecimal(values.get("8. Bid Price")),
                    new BigDecimal(values.get("9. Ask Price"))
            );
        }

    }
}
