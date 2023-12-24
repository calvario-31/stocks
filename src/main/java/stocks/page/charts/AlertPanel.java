package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stocks.page.BasePage;
import stocks.utilities.Logs;

import java.time.Duration;

public class AlertPanel extends BasePage {
    private final By threeDotsButton = By.cssSelector("div[data-name='alerts-settings-button']");
    private final By removeAllAlertsButton = By.xpath("//span[text()='Remove all']");
    private final By yesButton = By.cssSelector("button[name='yes']");

    public AlertPanel(WebDriver driver) {
        super(driver);
    }

    public void removeAllAlertsButton() {
        Logs.info("Clicking three dots");
        wait.until(ExpectedConditions.visibilityOfElementLocated(threeDotsButton)).click();
        Logs.info("Removing all alerts");
        wait.until(ExpectedConditions.visibilityOfElementLocated(removeAllAlertsButton)).click();

        try {
            Logs.info("Clicking yes on the modal");
            final var customWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            customWait.until(ExpectedConditions.visibilityOfElementLocated(yesButton)).click();
            Logs.info("Deleting alerts");
        } catch (TimeoutException timeoutException) {
            Logs.info("No alerts present");
        }
    }

    public boolean isOpen() {
        try {
            driver.findElement(threeDotsButton);
            Logs.info("alert panel opened");
            return true;
        } catch (NoSuchElementException noSuchElementException) {
            Logs.info("alert panel closed");
            return false;
        }
    }

    @Override
    public void waitPageToLoad() {
    }
}
