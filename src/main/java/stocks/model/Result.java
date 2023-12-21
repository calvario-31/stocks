package stocks.model;

public record Result(
        double netProfit1,
        double netProfit2,
        int totalClosedTrades,
        double percentProfitable,
        double profitFactor,
        double maxDrawDown1,
        double maxDrawDown2,
        double avgTrade1,
        double avgTrade2,
        int avgBars
) {

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
        return String.format(multilinea, netProfit1, netProfit2, totalClosedTrades, percentProfitable,
                profitFactor, maxDrawDown1, maxDrawDown2, avgTrade1, avgTrade2, avgBars);
    }
}
