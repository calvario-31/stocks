package stocks.parser;

import stocks.model.Data;
import stocks.model.SingleData;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
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
                singleData = new SingleData(Integer.parseInt(value));
            } else if (isBoolean(value)) {
                singleData = new SingleData(Boolean.parseBoolean(value));
            } else {
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
        final var lowerStr = str.toLowerCase();
        return "true".equals(lowerStr) || "false".equals(lowerStr);
    }
}
