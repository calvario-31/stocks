package stocks;

import org.openqa.selenium.WebDriver;
import stocks.model.Data;
import stocks.model.ResultFilter;
import stocks.utilities.AutomationUtils;
import stocks.utilities.FileManager;
import stocks.utilities.Flows;
import stocks.utilities.Logs;
import stocks.utilities.TestData;

public class Main {
    private static WebDriver driver;
    private static final String csvPath = "src/test/resources/data/input.csv";
    private static final String propertiesFilePath = "src/test/resources/data/info.properties";
    private static final String filtersFilePath = "src/test/resources/data/filter.properties";

    public static void main(String[] args) {
        try {
            final var filterProperties = FileManager.readProperties(filtersFilePath);
            final var properties = FileManager.readProperties(propertiesFilePath);
            FileManager.redirectStdErr();

            driver = AutomationUtils.setupDriver();

            final var flows = new Flows(driver);

            final var credentials = TestData.getCredentials(properties);
            flows.goToHomePage(credentials.username(), credentials.password());

            final var allData = TestData.getAllData(csvPath);

            flows.selectInterval(Integer.parseInt(properties.getProperty("interval"))); //1min
            flows.selectStrategy(properties.getProperty("strategy")); //strategy name

            for (var data : allData) {
                flows.fillData(data, new ResultFilter(filterProperties));
            }

            final var message = """
                                        
                    *******************************************
                    BEST:
                    *******************************************
                    %s
                    """;

            Logs.info(message, Data.getBestData());
            System.out.printf(message, Data.getBestData());
        } catch (Exception e) {
            Logs.error("Exception: %s, %s", e.getClass().getSimpleName(), e.getLocalizedMessage());
        } finally {
            AutomationUtils.killDriver(driver);
        }
    }
}
