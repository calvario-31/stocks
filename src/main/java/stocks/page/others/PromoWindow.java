package stocks.page.others;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stocks.page.BasePage;
import stocks.utilities.Logs;

public class PromoWindow extends BasePage {
    private final By dialog = By.cssSelector("div[data-dialog-name='gopro']");
    private final By popUpCloseButton = By.cssSelector("button[aria-label='Close']");

    public PromoWindow(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(dialog, this.getClass().getSimpleName());
    }

    public void closeModal() {
        Logs.info("Closing popup");
        final var popUpCLoseButtonElement = wait
                .until(ExpectedConditions.visibilityOfElementLocated(popUpCloseButton));

        new Actions(driver)
                .doubleClick(popUpCLoseButtonElement)
                .perform();
    }

    @Override
    public void goToPage() {

    }
}
