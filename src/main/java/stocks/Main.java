package stocks;

import org.openqa.selenium.WebDriver;
import stocks.utilities.AutomationUtils;
import stocks.utilities.FileManager;
import stocks.utilities.Flows;
import stocks.utilities.Logs;
import stocks.utilities.TestData;

public class Main {
    private static WebDriver driver;
    private static final String strategy = "Hull suite strategy";
    private static final String csvPath = "src/test/resources/data/input.csv";
    private static final String credentialsFilePath = "src/test/resources/data/credentials.txt";

    public static void main(String[] args) {
        try {
            FileManager.redirectStdErr();
            driver = AutomationUtils.setupDriver();

            final var flows = new Flows(driver);

            final var credentials = TestData.getCredentials(credentialsFilePath);
            flows.goToHomePage(credentials.username(), credentials.password());

            final var allData = TestData.getAllData(csvPath);

            flows.selectInterval(1); //1min
            flows.selectStrategy(strategy); //strategy name

            for (var data : allData) {
                flows.fillData(data);
            }

        } catch (Exception e) {
            Logs.error(e.getLocalizedMessage());
        } finally {
            AutomationUtils.killDriver(driver);
        }
    }
}
