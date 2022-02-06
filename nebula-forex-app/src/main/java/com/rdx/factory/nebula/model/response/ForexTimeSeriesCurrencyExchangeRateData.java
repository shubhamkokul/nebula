package com.rdx.factory.nebula.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class ForexTimeSeriesCurrencyExchangeRateData extends NebulaResponse {
    private final ForexMetaData forexMetaData;
    private final List<NebulaTimeSeries> fxTimeSeries;

    private ForexTimeSeriesCurrencyExchangeRateData(Builder builder) {
        this.forexMetaData = builder.forexMetaData;
        this.fxTimeSeries = builder.fxTimeSeries;
        this.statusCode = builder.statusCode;
        this.errorMessage = builder.errorMessage;
    }

    public static class Builder {
        private ForexMetaData forexMetaData;
        private List<NebulaTimeSeries> fxTimeSeries;
        private HttpStatus statusCode;
        private String errorMessage;

        public Builder forexMetaData(ForexMetaData forexMetaData) {
            this.forexMetaData = forexMetaData;
            return this;
        }

        public Builder fxTimeSeries(List<NebulaTimeSeries> fxTimeSeries) {
            this.fxTimeSeries = fxTimeSeries;
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

        public ForexTimeSeriesCurrencyExchangeRateData build() {
            return new ForexTimeSeriesCurrencyExchangeRateData(this);
        }
    }
}
