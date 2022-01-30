package com.rdx.factory.nebula.util;

import com.rdx.factory.nebula.service.NebulaAPIParameter;
import lombok.Getter;
import org.springframework.lang.Nullable;

@Getter
public class NebulaAPIParameterBuilder {
    private StringBuilder urlBuilder;

    public NebulaAPIParameterBuilder() {
        this.urlBuilder = new StringBuilder();
    }

    public void append(@Nullable NebulaAPIParameter apiParameter) {
        if (apiParameter != null) {
            append(apiParameter.getKey(), apiParameter.getValue());
        }
    }

    public void append(String key, String value) {
        String parameter = "&" + key + "=" + value;
        this.urlBuilder.append(parameter);
    }
}
