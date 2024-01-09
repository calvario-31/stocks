package stocks.parser;

import stocks.model.Data;
import stocks.model.SingleData;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    public static List<Data> createDataList(Object[][] objectMatrix) {
        final var listData = new ArrayList<Data>(); // all

        for (var outerArray : objectMatrix) {
            final var singleData = new ArrayList<SingleData>();

            for (var o : outerArray) {
                if (o instanceof Integer) {
                    singleData.add(new SingleData((Integer) o));
                } else if (o instanceof Boolean) {
                    singleData.add(new SingleData((Boolean) o));
                } else {
                    singleData.add(new SingleData((String) o));
                }
            }
            listData.add(new Data(singleData));
        }
        return listData;
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
