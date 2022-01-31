package com.rdx.factory.nebula.model.response;

import lombok.Getter;

@Getter
public class ForexMetaData extends NebulaMetaData {
    private final String fromSymbol;
    private final String toSymbol;

    private ForexMetaData(Builder builder) {
        this.information = builder.information;
        this.fromSymbol = builder.fromSymbol;
        this.toSymbol = builder.toSymbol;
        this.lastRefreshed = builder.lastRefreshed;
        this.interval = builder.interval;
        this.outputSize = builder.outputSize;
        this.timeZone = builder.timeZone;
    }

    public static class Builder {
        private String information;
        private String fromSymbol;
        private String toSymbol;
        private String lastRefreshed;
        private String interval;
        private String outputSize;
        private String timeZone;

        public Builder information(String information) {
            this.information = information;
            return this;
        }

        public Builder fromSymbol(String fromSymbol) {
            this.fromSymbol = fromSymbol;
            return this;
        }

        public Builder toSymbol(String toSymbol) {
            this.toSymbol = toSymbol;
            return this;
        }

        public Builder lastRefreshed(String lastRefreshed) {
            this.lastRefreshed = lastRefreshed;
            return this;
        }

        public Builder interval(String interval) {
            this.interval = interval;
            return this;
        }

        public Builder outputSize(String outputSize) {
            this.outputSize = outputSize;
            return this;
        }

        public Builder timeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public ForexMetaData build() {
            return new ForexMetaData(this);
        }
    }
}
