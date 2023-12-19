package stocks.utilities;

public class StringUtils {
    public static double removeUsd(String price) {
        final var priceWithoutDolar = removeSymbol(price, "USD");
        return getFinalPrice(priceWithoutDolar);
    }

    public static double removePercentage(String price) {
        final var priceWithoutPercentage = removeSymbol(price, "%");
        return getFinalPrice(priceWithoutPercentage);
    }

    private static String removeSymbol(String price, String symbol) {
        final var trimedPrice = price.replaceAll("\\s++", "");
        final var percentageIndex = trimedPrice.indexOf(symbol);
        return trimedPrice.substring(0, percentageIndex);
    }

    private static double getFinalPrice(String prince) {
        if (Character.isDigit(prince.charAt(0))) {
            return Double.parseDouble(prince);
        } else {
            return -1 * Double.parseDouble(prince.substring(1));
        }
    }
}
