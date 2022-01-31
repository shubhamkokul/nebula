package com.rdx.factory.nebula.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

@Getter
public class ForexCurrencyExchangeRateData extends NebulaResponse {
    private final String fromCurrencyCode;
    private final String fromCurrencyName;
    private final String toCurrencyCode;
    private final String toCurrencyName;
    private final BigDecimal exchangeRate;
    private final String lastRefreshed;
    private final String timeZone;
    private final BigDecimal latestBidPrice;
    private final BigDecimal latestAskPrice;

    private ForexCurrencyExchangeRateData(Builder builder) {
        this.fromCurrencyCode = builder.fromCurrencyCode;
        this.fromCurrencyName = builder.fromCurrencyName;
        this.toCurrencyCode = builder.toCurrencyCode;
        this.toCurrencyName = builder.toCurrencyName;
        this.exchangeRate = builder.exchangeRate;
        this.lastRefreshed = builder.lastRefreshed;
        this.timeZone = builder.timeZone;
        this.latestBidPrice = builder.latestBidPrice;
        this.latestAskPrice = builder.latestAskPrice;
        this.statusCode = builder.statusCode;
        this.errorMessage = builder.errorMessage;
    }

    @Override
    public String toString() {
        return "ForexCurrencyExchangeRateData{" +
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

    public static class Builder {
        private String fromCurrencyCode;
        private String fromCurrencyName;
        private String toCurrencyCode;
        private String toCurrencyName;
        private BigDecimal exchangeRate;
        private String lastRefreshed;
        private String timeZone;
        private BigDecimal latestBidPrice;
        private BigDecimal latestAskPrice;
        private HttpStatus statusCode;
        private String errorMessage;

        public Builder fromCurrencyCode(String fromCurrencyCode) {
            this.fromCurrencyCode = fromCurrencyCode;
            return this;
        }

        public Builder fromCurrencyName(String fromCurrencyName) {
            this.fromCurrencyName = fromCurrencyName;
            return this;
        }

        public Builder toCurrencyCode(String toCurrencyCode) {
            this.toCurrencyCode = toCurrencyCode;
            return this;
        }

        public Builder toCurrencyName(String toCurrencyName) {
            this.toCurrencyName = toCurrencyName;
            return this;
        }

        public Builder exchangeRate(BigDecimal exchangeRate) {
            this.exchangeRate = exchangeRate;
            return this;
        }

        public Builder lastRefreshed(String lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
            return this;
        }

        public Builder timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder latestBidPrice(BigDecimal latestBidPrice) {
            this.latestBidPrice = latestBidPrice;
            return this;
        }

        public Builder latestAskPrice(BigDecimal latestAskPrice) {
            this.latestAskPrice = latestAskPrice;
            return this;
        }

        public Builder statusCode(HttpStatus statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ForexCurrencyExchangeRateData build() {
            return new ForexCurrencyExchangeRateData(this);
        }
    }
}
