package com.rdx.factory.nebula.model.output;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter @NoArgsConstructor
public class ExchangeRate implements Serializable {
    private String fromCurrencyCode;
    private String fromCurrencyName;
    private String toCurrencyCode;
    private String toCurrencyName;
    private BigDecimal exchangeRate;
    private LocalDateTime lastRefreshed;
    private String timeZone;
    private BigDecimal latestBidPrice;
    private BigDecimal latestAskPrice;

    @Override
    public String toString() {
        return "ExchangeRate{" +
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
