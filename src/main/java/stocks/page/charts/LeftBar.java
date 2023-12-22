package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class LeftBar extends BasePage {
    private final By thrashIcon = By.cssSelector("button[data-tooltip='Remove objects']");
    private final By removeIndicators = By.cssSelector("div[data-name='remove-studies']");

    public LeftBar(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {

    }

    public void removingIndicators() {
        Logs.info("Removing indicators");
        driver.findElement(thrashIcon).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeIndicators)).click();
    }
}
