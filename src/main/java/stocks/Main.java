package stocks;

import org.openqa.selenium.WebDriver;
import stocks.api.CombinationApi;
import stocks.api.FilterApi;
import stocks.model.Data;
import stocks.utilities.AutomationUtils;
import stocks.utilities.FileManager;
import stocks.utilities.Flows;
import stocks.utilities.Logs;
import stocks.utilities.TestData;

public class Main {
    private static WebDriver driver;
    public static final String propertiesFilePath = "src/main/resources/info.properties";

    public static void main(String[] args) {
        try {
            FileManager.redirectStdErr();
            final var properties = FileManager.readProperties(propertiesFilePath);
            final var allData = CombinationApi.getCombinations();
            final var credentials = TestData.getCredentials(properties);

            driver = AutomationUtils.setupDriver();

            final var flows = new Flows(driver);

            //login and go to superchart page
            flows.goToHomePage(
                    credentials.username(),
                    credentials.password()
            );

            //interval, strategy, delete alerts
            flows.setupPreconditions(
                    Integer.parseInt(properties.getProperty("interval")),
                    properties.getProperty("strategy")
            );

            //assign first filters according previous data
            final var filters = FilterApi.getFilters(Data.getPreviousBestData());

            for (var data : allData) {
                flows.fillData(
                        data,
                        filters
                );
            }

            final var isNewBest = flows.isNewBest();

            flows.alertBestResults(isNewBest);

            printMessage();

            AutomationUtils.automationSleep(3000);
        } catch (Exception e) {
            Logs.error("Exception: %s, %s", e.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            AutomationUtils.killDriver(driver);
        }
    }

    private static void printMessage() {
        final var message = """
                                    
                *******************************************
                BEST:
                *******************************************
                %s
                """;
        Logs.info(message, Data.getBestData());
        System.out.printf(message, Data.getBestData());
    }
}
