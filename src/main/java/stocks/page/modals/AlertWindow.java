package stocks.page.modals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class AlertWindow extends BasePage {
    private final By createButton = By.cssSelector("button[data-name='submit']");

    @Override
    public void waitPageToLoad() {
        waitPage(createButton, this.getClass().getSimpleName());
    }

    public AlertWindow(WebDriver driver) {
        super(driver);
    }

    public void clickCreate() {
        Logs.info("Creating alert");
        driver.findElement(createButton).click();
    }
}
