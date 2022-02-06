package com.rdx.factory.nebula.util;

import com.rdx.factory.nebula.exception.NebulaValidationException;
import com.rdx.factory.nebula.model.input.DataType;
import com.rdx.factory.nebula.model.input.Interval;
import com.rdx.factory.nebula.model.input.OutputSize;

public class ForexRequestUtil {

    public static Interval getDaily(String intervalInput) throws NebulaValidationException {
        if (intervalInput == null) {
            throw new NebulaValidationException("Please pass in valid interval");
        }
        int interval = 5;
        try {
            interval = Integer.parseInt(intervalInput);
        } catch(NumberFormatException e) {
            throw new NebulaValidationException("Please pass in valid interval in Integer");
        }
        if (interval <= 4)
            return Interval.ONE;
        if (interval <= 9)
            return Interval.FIVE;
        if (interval <= 14)
            return Interval.TEN;
        if (interval <= 29)
            return Interval.FIFTEEN;
        if (interval <= 59)
            return Interval.THIRTY;
        else
            return Interval.SIXTY;
    }

    public static String getDaily() {
        return "Daily";
    }

    public static String getWeekly() {
        return "Weekly";
    }

    public static OutputSize getOutputSize(String outputSize) throws NebulaValidationException {
        if (outputSize == null) return OutputSize.COMPACT;
        if (outputSize.equalsIgnoreCase("full")) return OutputSize.FULL;
        else return OutputSize.COMPACT;
    }

    public static DataType getDataType(String dataType) throws NebulaValidationException {
        if (dataType == null) return DataType.JSON;
        if (dataType.equalsIgnoreCase("csv")) return DataType.CSV;
        else return DataType.JSON;
    }
}
