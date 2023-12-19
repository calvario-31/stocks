package stocks.page.technical;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import stocks.page.BasePage;
import stocks.utilities.Logs;

import java.util.List;

public class TechnicalStrategy extends BasePage {
    private final By okButton = By.name("submit");
    private final By cancelButton = By.name("cancel");
    private final By inputs = By.xpath("//div[@data-name='indicator-properties-dialog']//div[contains(@class,'content')]/div[not(contains(@class, 'first'))]");

    private By getListOption(String value) {
        final var xpathString = String.format("//span[text()='%s']//ancestor::div[@role='option']", value);
        return By.xpath(xpathString);
    }

    public TechnicalStrategy(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitPageToLoad() {
        waitPage(okButton, this.getClass().getSimpleName());
    }

    public List<WebElement> getInputData() {
        return driver.findElements(inputs);
    }

    public void clickOnOk() {
        Logs.info("Clicking on ok button");
        driver.findElement(okButton).click();
    }

    public void selectListELement(String value, WebElement node) {
        Logs.debug("Selecting list value: %s", value);
        final var input = node.findElement(By.cssSelector("span[data-role='listbox']"));

        input.click();
        driver.findElement(getListOption(value)).click();
    }

    public void selectCheckboxValue(Boolean isEnabled, WebElement node) {
        Logs.debug("Setting checkbox to: %b", isEnabled);
        final var inputCheckbox = node.findElement(By.cssSelector("input"));

        final var currentlyIsEnabled = inputCheckbox.isSelected();

        final var input = node.findElement(By.cssSelector("label"));

        if (isEnabled && !currentlyIsEnabled) {
            input.click(); //should be enabled but in the ui is no enabled
        } else if (!isEnabled && currentlyIsEnabled) {
            input.click(); //should be disabeld but in the ui is not enabled
        }
    }

    public void fillNumericValue(int value, WebElement node) {
        Logs.debug("Setting value to: %d", value);
        final var input = node.findElement(By.cssSelector("input"));

        new Actions(driver)
                .doubleClick(input)
                .keyDown(Keys.DELETE)
                .keyUp(Keys.DELETE)
                .perform();

        input.sendKeys(String.valueOf(value));
    }

    @Override
    public void goToPage() {

    }
}
