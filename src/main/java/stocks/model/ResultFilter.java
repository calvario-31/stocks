package stocks.model;

import stocks.utilities.Logs;

import java.util.Properties;

public class ResultFilter {
    private final static String separator = ";";
    private final String netProfit1Filter;
    private final String netProfit2Filter;
    private final String totalClosedTradesFilter;
    private final String percentProfitableFilter;
    private final String profitFactorFilter;
    private final String maxDrawDown1Filter;
    private final String maxDrawDown2Filter;
    private final String avgTrade1Filter;
    private final String avgTrade2Filter;
    private final String avgBarsFilter;

    public ResultFilter(Properties properties) {
        netProfit1Filter = properties.getProperty("netProfit1");
        netProfit2Filter = properties.getProperty("netProfit2");
        totalClosedTradesFilter = properties.getProperty("totalClosedTrades");
        percentProfitableFilter = properties.getProperty("percentProfitable");
        profitFactorFilter = properties.getProperty("profitFactor");
        maxDrawDown1Filter = properties.getProperty("maxDrawDown1");
        maxDrawDown2Filter = properties.getProperty("maxDrawDown2");
        avgTrade1Filter = properties.getProperty("avgTrade1");
        avgTrade2Filter = properties.getProperty("avgTrade2");
        avgBarsFilter = properties.getProperty("avgBars");
    }

    public boolean isWantedResult(Result result) {
        final var isWantedResult =
                evaluateFilterDouble("netprofit1", netProfit1Filter, result.netProfit1()) &&
                evaluateFilterDouble("netprofit2", netProfit2Filter, result.netProfit2()) &&
                evaluateFilterInt("totalClosedTrades", totalClosedTradesFilter, result.totalClosedTrades()) &&
                evaluateFilterDouble("percentProfitable", percentProfitableFilter, result.percentProfitable()) &&
                evaluateFilterDouble("profitFactor", profitFactorFilter, result.profitFactor()) &&
                evaluateFilterDouble("maxDrawDown1", maxDrawDown1Filter, result.maxDrawDown1()) &&
                evaluateFilterDouble("maxDrawDown2", maxDrawDown2Filter, result.maxDrawDown2()) &&
                evaluateFilterDouble("avgTrade1", avgTrade1Filter, result.avgTrade1()) &&
                evaluateFilterDouble("avgTrade2", avgTrade2Filter, result.avgTrade2()) &&
                evaluateFilterInt("avgBars", avgBarsFilter, result.avgBars());

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
