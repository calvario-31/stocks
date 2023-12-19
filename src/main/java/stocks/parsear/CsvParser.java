package stocks.parsear;

import stocks.model.Data;
import stocks.model.SingleData;
import stocks.utilities.Logs;

import java.util.ArrayList;
import java.util.List;

public class CsvParser {
    public static List<Data> createDataList(List<String> rawInputList) {
        final var dataList = new ArrayList<Data>();

        for (var rawInput : rawInputList) {
            final var data = parseData(rawInput);
            dataList.add(data);
        }

        return dataList;
    }

    private static Data parseData(String rawInput) {
        final var singleDataList = new ArrayList<SingleData>();

        final var arrayString = rawInput.split(",");

        SingleData singleData;

        for (var value : arrayString) {
            if (isNumeric(value)) {
                Logs.debug("value: %s\tnumeric", value);
                singleData = new SingleData(Integer.parseInt(value));
            } else if (isBoolean(value)) {
                Logs.debug("value: %s\tboolean", value);
                singleData = new SingleData(Boolean.parseBoolean(value));
            } else {
                Logs.debug("value: %s\tstring", value);
                singleData = new SingleData(value);
            }

            singleDataList.add(singleData);
        }

        return new Data(singleDataList);
    }

    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isBoolean(String str) {
        return "true".equals(str) || "false".equals(str);
    }
}
