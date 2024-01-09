package stocks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Result(
        @JsonProperty("netProfit") double netProfit,
        @JsonProperty("grossProfit") double grossProfit,
        @JsonProperty("grossLoss") double grossLoss,
        @JsonProperty("maxRunUp") double maxRunUp,
        @JsonProperty("maxDrawdown") double maxDrawDown,
        @JsonProperty("buyHoldReturn") double buyHoldReturn,
        @JsonProperty("sharpeRatio") double sharpeRatio,
        @JsonProperty("sortinoRatio") double sortinoRatio,
        @JsonProperty("profitFactor") double profitFactor,
        @JsonProperty("maxContractsHeld") int maxContractsHeld,
        @JsonProperty("openPL") double openPL,
        @JsonProperty("commissionPaid") double commissionPaid,
        @JsonProperty("totalClosedTrades") int totalClosedTrades,
        @JsonProperty("totalOpenTrades") int totalOpenTrades,
        @JsonProperty("numberWinningTrades") int numberWinningTrades,
        @JsonProperty("numberLosingTrades") int numberLosingTrades,
        @JsonProperty("percentProfitable") double percentProfitable,
        @JsonProperty("avgTrade") double avgTrade,
        @JsonProperty("avgWinningTrade") double avgWinningTrade,
        @JsonProperty("avgLosingTrade") double avgLosingTrade,
        @JsonProperty("ratioAvgWinAvgLoss") double ratioAvgWinAvgLoss,
        @JsonProperty("largestWinningTrade") double largestWinningTrade,
        @JsonProperty("largestLosingTrade") double largestLosingTrade,
        @JsonProperty("avgBarsInTrades") int avgBarsInTrades,
        @JsonProperty("avgBarsInWinningTrades") int avgBarsInWinningTrades,
        @JsonProperty("avgBarsInLosingTrades") int avgBarsInLosingTrades,
        @JsonProperty("marginCalls") int marginCalls,
        double maxDrawDown1,
        double avgTrade1,
        double netProfit1
) {
    public Result(double netProfit1, double netProfit, int totalClosedTrades, double percentProfitable, double profitFactor, double maxDrawDown1, double maxDrawDown, double avgTrade1, double avgTrade, int avgBarsInTrades) {
        this(netProfit, 0, 0, 0, maxDrawDown, 0, 0, 0, profitFactor, 0, 0, 0, totalClosedTrades, 0, 0, 0, percentProfitable, avgTrade, 0, 0, 0, 0, 0, avgBarsInTrades, 0, 0, 0, maxDrawDown1, avgTrade1, netProfit1);
    }

    @Override
    public String toString() {
        final var multilinea = """
                {
                    netProfit1: %.2f
                    netProfit2: %.2f
                    totalClosedTrades: %d
                    percentProfitable: %.2f
                    profitFactor: %.2f
                    maxDrawDown1: %.2f
                    maxDrawDown2: %.2f
                    avgTrade1: %.2f
                    avgTrade2: %.2f
                    avgNumberBarsInTrades: %d
                }
                """;
        return String.format(multilinea, netProfit1, netProfit, totalClosedTrades, percentProfitable,
                profitFactor, maxDrawDown1, maxDrawDown, avgTrade1, avgTrade, avgBarsInTrades);
    }
}
