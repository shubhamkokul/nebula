package com.rdx.factory.nebula.model.response;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
public class NebulaTimeSeries implements Serializable {
    private final String timeStamp;
    private final BigDecimal open;
    private final BigDecimal high;
    private final BigDecimal low;
    private final BigDecimal close;

    private NebulaTimeSeries(Builder builder) {
        this.timeStamp = builder.timeStamp;
        this.open = builder.open;
        this.high = builder.high;
        this.low = builder.low;
        this.close = builder.close;
    }

    public static class Builder {
        private String timeStamp;
        private BigDecimal open;
        private BigDecimal high;
        private BigDecimal low;
        private BigDecimal close;

        public Builder timeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder open(BigDecimal open) {
            this.open = open;
            return this;
        }

        public Builder high(BigDecimal high) {
            this.high = high;
            return this;
        }

        public Builder low(BigDecimal low) {
            this.low = low;
            return this;
        }

        public Builder close(BigDecimal close) {
            this.close = close;
            return this;
        }

        public NebulaTimeSeries build() {
            return new NebulaTimeSeries(this);
        }
    }
}
