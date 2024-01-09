package stocks.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import stocks.utilities.Logs;

public record ResultFilter(
        @JsonProperty("netProfit") String netProfitFilter,
        @JsonProperty("grossProfit") String grossProfitFilter,
        @JsonProperty("grossLoss") String grossLossFilter,
        @JsonProperty("maxRunUp") String maxRunUpFilter,
        @JsonProperty("maxDrawdown") String maxDrawDownFilter,
        @JsonProperty("buyHoldReturn") String buyHoldReturnFilter,
        @JsonProperty("sharpeRatio") String sharpeRatioFilter,
        @JsonProperty("sortinoRatio") String sortinoRatioFilter,
        @JsonProperty("profitFactor") String profitFactorFilter,
        @JsonProperty("maxContractsHeld") String maxContractsHeldFilter,
        @JsonProperty("openPL") String openPLFilter,
        @JsonProperty("commissionPaid") String commissionPaidFilter,
        @JsonProperty("totalClosedTrades") String totalClosedTradesFilter,
        @JsonProperty("totalOpenTrades") String totalOpenTradesFilter,
        @JsonProperty("numberWinningTrades") String numberWinningTradesFilter,
        @JsonProperty("numberLosingTrades") String numberLosingTradesFilter,
        @JsonProperty("percentProfitable") String percentProfitableFilter,
        @JsonProperty("avgTrade") String avgTradeFilter,
        @JsonProperty("avgWinningTrade") String avgWinningTradeFilter,
        @JsonProperty("avgLosingTrade") String avgLosingTradeFilter,
        @JsonProperty("ratioAvgWinAvgLoss") String ratioAvgWinAvgLossFilter,
        @JsonProperty("largestWinningTrade") String largestWinningTradeFilter,
        @JsonProperty("largestLosingTrade") String largestLosingTradeFilter,
        @JsonProperty("avgBarsInTrades") String avgBarsInTradesFilter,
        @JsonProperty("avgBarsInWinningTrades") String avgBarsInWinningTradesFilter,
        @JsonProperty("avgBarsInLosingTrades") String avgBarsInLosingTradesFilter,
        @JsonProperty("marginCalls") String marginCallsFilter
) {
    private final static String separator = ";";

    public boolean isWantedResult(Result result) {
        final var isWantedResult =
                evaluateFilterDouble("netProfit", netProfitFilter, result.netProfit()) &&
                evaluateFilterInt("totalClosedTrades", totalClosedTradesFilter, result.totalClosedTrades()) &&
                evaluateFilterDouble("percentProfitable", percentProfitableFilter, result.percentProfitable()) &&
                evaluateFilterDouble("profitFactor", profitFactorFilter, result.profitFactor()) &&
                evaluateFilterDouble("maxDrawDown", maxDrawDownFilter, result.maxDrawDown()) &&
                evaluateFilterDouble("avgTrade", avgTradeFilter, result.avgTrade());

        Logs.debug("is wanted result? %b", isWantedResult);
        return isWantedResult;
    }

    private boolean evaluateFilterDouble(String name, String currentFilter, double resultValue) {
        Logs.trace("""
                                
                double
                evaluating: %s
                filter: %s
                result value: %.2f
                """, name, currentFilter, resultValue);
        if (isActiveFilter(currentFilter)) {
            final var operator = getOperator(currentFilter);
            final var number = getNumberDouble(currentFilter);

            final var result = switch (operator) {
                case ">" -> resultValue > number;
                case "<" -> resultValue < number;
                case ">=" -> resultValue >= number;
                case "<=" -> resultValue <= number;
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            };
            Logs.trace("filter %s pass? %b", name, result);
            return result;
        } else {
            Logs.trace("filter %s is not active", name);
            return true; //if not active then return true
        }
    }

    private boolean evaluateFilterInt(String name, String currentFilter, int resultValue) {
        Logs.trace("""
                                
                int
                evaluating: %s
                filter: %s
                result value: %d
                """, name, currentFilter, resultValue);
        if (isActiveFilter(currentFilter)) {
            final var operator = getOperator(currentFilter);
            final var number = getNumberInt(currentFilter);

            final var result = switch (operator) {
                case ">" -> resultValue > number;
                case "<" -> resultValue < number;
                case ">=" -> resultValue >= number;
                case "<=" -> resultValue <= number;
                default -> throw new IllegalStateException("Unexpected value: " + operator);
            };
            Logs.trace("filter %s pass? %b", name, result);
            return result;
        } else {
            Logs.trace("filter %s is not active", name);
            return true; //if not active then return true
        }
    }

    private boolean isActiveFilter(String currentFilter) {
        return !currentFilter.equals("0"); //if it is 0 then is not active
    }

    private String getOperator(String currentFilter) {
        return currentFilter.split(separator)[0];
    }

    private double getNumberDouble(String currentFilter) {
        return Double.parseDouble(currentFilter.split(separator)[1]);
    }

    private double getNumberInt(String currentFilter) {
        return Integer.parseInt(currentFilter.split(separator)[1]);
    }
}
