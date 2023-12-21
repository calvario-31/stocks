package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;
import stocks.utilities.AutomationUtils;
import stocks.utilities.Logs;

public class IndicatorsWindow extends BasePage {
    private final By strategiesTab = By.id("strategies");
    private final By searchInput = By.cssSelector("input[data-role='search']");
    private final By closeButton = By.cssSelector("button[data-name='close']");
    private final By results = By.cssSelector("div[data-role='list-item']");

    @Override
    public void waitPageToLoad() {
        waitPage(strategiesTab, this.getClass().getSimpleName());
    }

    public IndicatorsWindow(WebDriver driver) {
        super(driver);
    }

    public void selectStrategy(String strategyName) {
        final var searchInputElement = driver.findElement(searchInput);

        Logs.info("Searching %s strategy", strategyName);
        searchInputElement.clear();
        searchInputElement.sendKeys(strategyName);

        AutomationUtils.automationSleep(1000);

        final var resultsList = driver.findElements(results);

        Logs.info("Selecting first result");
        resultsList.get(0).click();
    }

    public void closeWindow() {
        Logs.info("Closing window");
        driver.findElement(closeButton).click();
    }

    @Override
    public void goToPage() {

    }
}
