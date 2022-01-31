package com.rdx.factory.nebula.model.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor
public class CurrencyExchangeRateData implements Serializable {
    private String fromCurrencyCode;
    private String fromCurrencyName;
    private String toCurrencyCode;
    private String toCurrencyName;
    private BigDecimal exchangeRate;
    private String lastRefreshed;
    private String timeZone;
    private BigDecimal latestBidPrice;
    private BigDecimal latestAskPrice;

    @Override
    public String toString() {
        return "CurrencyExchangeRateData{" +
                "fromCurrencyCode='" + fromCurrencyCode + '\'' +
                ", fromCurrencyName='" + fromCurrencyName + '\'' +
                ", toCurrencyCode='" + toCurrencyCode + '\'' +
                ", toCurrencyName='" + toCurrencyName + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", lastRefreshed=" + lastRefreshed +
                ", timeZone='" + timeZone + '\'' +
                ", latestBidPrice=" + latestBidPrice +
                ", latestAskPrice=" + latestAskPrice +
                '}';
    }
}
