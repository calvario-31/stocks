package stocks.page.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class AlertWindow extends BasePage {
    private final By createButton = By.cssSelector("button[data-name='submit']");
    private final By secondSelect = By.cssSelector("span[data-name='operator-select']");
    private final By alertOnlyOption = By.id("id_item_alerts");

    @Override
    public void waitPageToLoad() {
        waitPage(createButton, this.getClass().getSimpleName());
    }

    public AlertWindow(WebDriver driver) {
        super(driver);
    }

    public void createAlert() {
        try {
            Logs.info("Checking if second select is present");
            driver.findElement(secondSelect).click();
            Logs.info("Selecting alert only option");
            driver.findElement(alertOnlyOption).click();
        } catch (NoSuchElementException noSuchElementException) {
            Logs.info("second alert was not present");
        }

        Logs.info("Creating alert");
        driver.findElement(createButton).click();
    }
}
