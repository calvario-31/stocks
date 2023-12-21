package stocks.page.charts;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stocks.model.Result;
import stocks.page.BasePage;
import stocks.utilities.Logs;
import stocks.utilities.StringUtils;

import java.time.Duration;

public class SuperChartBottomBar extends BasePage {
    private final By cogButton = By.cssSelector("button[title='Settings']");
    private final By clockButton = By.cssSelector("button[title='Add alert']");
    private final By results = By.xpath("//div[contains(@class,'secondRow')]");
    private final By noResults = By.xpath("//span[text()='Caution!']");

    public SuperChartBottomBar(WebDriver driver) {
        super(driver);
    }

    public void clickSettingsButton() {
        Logs.info("Clicking on settings options");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cogButton)).click();
    }

    public Result getCurrentResults() {
        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        try {
            //can trigger the exception is not visible within 3 sec
            customWait.until(ExpectedConditions.visibilityOfElementLocated(results));

            final var resultList = driver.findElements(results);
            final var div = By.cssSelector("div");

            final var netProfitSubList = resultList.get(0).findElements(div);
            final var netProfit1 = StringUtils.removeUsd(netProfitSubList.get(0).getText());
            final var netProfit2 = StringUtils.removePercentage(netProfitSubList.get(1).getText());

            final var totalClosedTrades = Integer.parseInt(resultList.get(1).findElement(div).getText());
            final var percentProfitable = StringUtils.removePercentage(resultList.get(2).findElement(div).getText());
            final var profitFactor = Double.parseDouble(resultList.get(3).findElement(div).getText());

            final var maxDrawDownSubList = resultList.get(4).findElements(div);
            final var maxDrawDown1 = StringUtils.removeUsd(maxDrawDownSubList.get(0).getText());
            final var maxDrawDown2 = StringUtils.removePercentage(maxDrawDownSubList.get(1).getText());

            final var avgTradeSubList = resultList.get(5).findElements(div);
            final var avgTrade1 = StringUtils.removeUsd(avgTradeSubList.get(0).getText());
            final var avgTrade2 = StringUtils.removePercentage(avgTradeSubList.get(1).getText());

            final var avgNumberBarsInTrades = Integer.parseInt(resultList.get(6).findElement(div).getText());

            return new Result(
                    netProfit1, netProfit2, totalClosedTrades, percentProfitable,
                    profitFactor, maxDrawDown1, maxDrawDown2, avgTrade1, avgTrade2, avgNumberBarsInTrades
            );
        } catch (TimeoutException e) { //no results
            Logs.error("Catch TimeoutException no results: %s", e.getLocalizedMessage());
            assert driver.findElement(noResults).isDisplayed(); //no results prompt should appear
            return null;
        }
    }

    public void clickClockButton() {
        Logs.info("Clicking on settings options");
        driver.findElement(clockButton).click();
    }

    @Override
    public void goToPage() {

    }

    @Override
    public void waitPageToLoad() {

    }
}
