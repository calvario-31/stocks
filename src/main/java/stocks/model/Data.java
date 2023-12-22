package stocks.model;

import stocks.page.modals.TechnicalStrategyWindow;
import stocks.utilities.Logs;

import java.util.List;

public class Data {
    private static Data bestData = null;
    private final List<SingleData> listData;
    private Result result;

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
            if (bestData.result.netProfit2() < result.netProfit2()) {
                bestData = new Data(technicalStrategyWindow.getCurrentInfo(), result);
                printNewResult();
            }
        }
    }

    public static Data getBestData() {
        return bestData;
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
