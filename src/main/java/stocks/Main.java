package stocks;

import org.openqa.selenium.WebDriver;
import stocks.utilities.AutomationUtils;
import stocks.utilities.FileManager;
import stocks.utilities.Flows;
import stocks.utilities.Logs;
import stocks.utilities.TestData;

public class Main {
    private static WebDriver driver;

    public static void main(String[] args) {
        try {
            FileManager.redirectStdErr();
            driver = AutomationUtils.setupDriver();

            final var flows = new Flows(driver);

            final var credentials = TestData.getCredentials2();
            flows.goToHomePage(credentials.username(), credentials.password());

            flows.selectInterval(1); //1min
            flows.selectStrategy("Hull suite strategy"); //strategy name

            final var allData = TestData.getAllData();
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
