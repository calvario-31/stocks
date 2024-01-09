package stocks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import stocks.page.modals.TechnicalStrategyWindow;
import stocks.utilities.Logs;

import java.util.List;

public class Data {
    private static Data previousBestData;
    private static Data bestData = null;
    @JsonProperty("combination")
    private List<SingleData> listData;
    @JsonProperty("result")
    private Result result;

    public Data() { //needed to deserialize
    }

    public Data(List<SingleData> listData) {
        this.listData = listData;
    }

    public Data(List<SingleData> listData, Result result) {
        this.listData = listData;
        this.result = result;
    }

    public List<SingleData> getListData() {
        return listData;
    }

    public static void compareUpdate(Result result, TechnicalStrategyWindow technicalStrategyWindow) {
        if (bestData == null) {
            bestData = new Data(technicalStrategyWindow.getCurrentInfo(), result);
            printNewResult();
        } else {
            if (bestData.result.netProfit() < result.netProfit()) {
                bestData = new Data(technicalStrategyWindow.getCurrentInfo(), result);
                printNewResult();
            }
        }
    }

    public static void setBestData(Data bestData) {
        Data.bestData = bestData;
    }

    public static Data getBestData() {
        return bestData;
    }

    public static void setPreviousBestResult(Data previousBestData) {
        Data.previousBestData = previousBestData;
    }

    public static Data getPreviousBestData() {
        return previousBestData;
    }

    public Result getResult() {
        return result;
    }

    private static void printNewResult() {
        Logs.debug("""
                                
                new best:
                %s
                """, bestData
        );
    }

    @Override
    public String toString() {
        final var multilinea = """
                combination: %s
                result: %s
                """;
        return String.format(
                multilinea,
                listData,
                result
        );
    }
}
