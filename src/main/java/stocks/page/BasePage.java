package stocks.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stocks.utilities.Logs;

import java.time.Duration;

public abstract class BasePage {
    protected final String baseUrl = "https://www.tradingview.com";
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    protected void waitPage(By locator, String pageName) {
        Logs.info("Waiting %s to load", pageName);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Logs.info("%s loaded successfully", pageName);
    }

    public abstract void waitPageToLoad();
}
