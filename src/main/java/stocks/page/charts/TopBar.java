package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class TopBar extends BasePage {
    private final By indicatorsOption = By.id("header-toolbar-indicators");
    private final By intervalOption = By.id("header-toolbar-intervals");
    private final By saveButton = By.id("header-toolbar-save-load");

    private By getInterval(int dataValue) {
        final var cssString = String.format("div[data-value='%d']", dataValue);
        return By.cssSelector(cssString);
    }

    public TopBar(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(indicatorsOption, this.getClass().getSimpleName());
    }

    public void selectInterval(int intervalTime) {
        driver.findElement(intervalOption).click();

        Logs.info("Selecting interval to: %d", intervalTime);
        driver.findElement(getInterval(intervalTime)).click();
    }

    public void clickIndicatorsTab() {
        Logs.info("Clicking indicators tab");
        driver.findElement(indicatorsOption).click();
    }

    public void clickOnSave() {
        Logs.info("Clicking on save changes");
        driver.findElement(saveButton).click();
    }
}
