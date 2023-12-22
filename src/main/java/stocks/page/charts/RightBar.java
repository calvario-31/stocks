package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class RightBar extends BasePage {
    private final By alertButton = By.cssSelector("button[data-tooltip='Alerts']");

    public RightBar(WebDriver driver) {
        super(driver);
    }

    public void clickAlertButton() {
        Logs.info("Clicking on alert button");
        driver.findElement(alertButton).click();
    }

    @Override
    public void waitPageToLoad() {

    }
}
