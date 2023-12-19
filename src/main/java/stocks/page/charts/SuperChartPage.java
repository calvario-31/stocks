package stocks.page.charts;

import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;

public class SuperChartPage extends BasePage {
    public SuperChartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {

    }

    @Override
    public void goToPage() {
        final var url = String.format("%s/chart/", baseUrl);
        driver.get(url);
    }
}
