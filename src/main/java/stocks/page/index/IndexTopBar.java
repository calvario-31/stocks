package stocks.page.index;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class IndexTopBar extends BasePage {
    private final By productsOption = By.cssSelector("button[data-main-menu-root-track-id='products']");
    private final By superchartOption = By.xpath("//span[text()='Supercharts']");

    @Override
    public void waitPageToLoad() {
        waitPage(productsOption, this.getClass().getSimpleName());
    }

    public IndexTopBar(WebDriver driver) {
        super(driver);
    }

    public void goToSuperChart() {
        final var actions = new Actions(driver);

        Logs.info("Mouse over products");
        actions.moveToElement(driver.findElement(productsOption)).perform();

        Logs.info("Mouse over supercharts");
        actions.moveToElement(driver.findElement(superchartOption)).perform();

        Logs.info("Clicking superchart");
        actions.click().perform();
    }
}
