package stocks.page.index;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class IndexPage extends BasePage {
    private final By anonymousIcon = By.className("tv-header__user-menu-button--anonymous");
    private final By signInLabel = By.xpath("//span[text()='Sign in']");
    private final By mainPromoContainer = By.className("js-main-page-promo-container");

    @Override
    public void waitPageToLoad() {
        waitPage(mainPromoContainer, this.getClass().getSimpleName());
    }

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public void goToLogin() {
        Logs.info("Clicking people icon");
        driver.findElement(anonymousIcon).click();

        Logs.info("Clicking sign in label");
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInLabel)).click();
    }

    public void goToPage() {
        driver.get(baseUrl);
    }
}
