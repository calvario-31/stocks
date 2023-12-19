package stocks.page.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stocks.page.BasePage;
import stocks.utilities.AutomationUtils;
import stocks.utilities.Logs;

import java.time.Duration;

public class LoginPage extends BasePage {
    private final By emailLabel = By.name("Email");
    private final By usernameInput = By.name("id_username");
    private final By passwordInput = By.name("id_password");
    private final By signInButton = By.cssSelector("button[data-overflow-tooltip-text='Sign in']");

    //iframe locators
    private final By iframeLocator = By.cssSelector("iframe");
    private final By checkIcon = By.className("recaptcha-checkbox-checked");

    @Override
    public void waitPageToLoad() {
        waitPage(emailLabel, this.getClass().getSimpleName());
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void signIn(String username, String password) {
        Logs.info("Clicking email icon");
        driver.findElement(emailLabel).click();

        Logs.info("Filling user input");
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput))
                .sendKeys(username);

        Logs.info("Filling password input");
        driver.findElement(passwordInput).sendKeys(password);

        Logs.info("Clicking sign in button");
        driver.findElement(signInButton).click();

        Logs.debug("Changing context to iframe");
        final var iframeElement = wait.until(ExpectedConditions.visibilityOfElementLocated(iframeLocator));

        driver.switchTo().frame(iframeElement);

        final var customWait = new WebDriverWait(driver, Duration.ofSeconds(180));
        Logs.info("Waiting captach to be filled for 180 sec");
        customWait.until(ExpectedConditions.visibilityOfElementLocated(checkIcon));

        Logs.debug("Returning to default context");
        driver.switchTo().defaultContent();

        Logs.info("Clicking sign in button");
        driver.findElement(signInButton).click();

        AutomationUtils.automationSleep(2500);
    }

    @Override
    public void goToPage() {

    }
}
