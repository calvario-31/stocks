package stocks.page.technical;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import stocks.model.SingleData;
import stocks.page.BasePage;
import stocks.utilities.Logs;

import java.util.ArrayList;
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
        Logs.trace("Selecting list value: %s", value);
        final var input = node.findElement(By.cssSelector("span[data-role='listbox']"));

        input.click();
        driver.findElement(getListOption(value)).click();
    }

    public void selectCheckboxValue(Boolean isEnabled, WebElement node) {
        Logs.trace("Setting checkbox to: %b", isEnabled);
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
        Logs.trace("Setting value to: %d", value);
        final var input = node.findElement(By.cssSelector("input"));

        new Actions(driver)
                .doubleClick(input)
                .keyDown(Keys.DELETE)
                .keyUp(Keys.DELETE)
                .perform();

        input.sendKeys(String.valueOf(value));
    }

    public List<SingleData> getCurrentInfo() {
        final var infoList = new ArrayList<SingleData>();
        final var inputList = getInputData();

        for (var input : inputList) {
            if (isNumeric(input)) {
                final var number = Integer.parseInt(input.findElement(By.xpath(".//input[@inputmode='numeric']")).getAttribute("value"));
                infoList.add(new SingleData(number));
            } else if (isCheckbox(input)) {
                final var checked = input.findElement(By.xpath(".//input[@type='checkbox']")).getAttribute("value");
                if (checked.equals("on")) {
                    infoList.add(new SingleData(true));
                } else {
                    infoList.add(new SingleData(false));
                }
            } else if (isList(input)) {
                final var text = input.findElement(By.xpath(".//span[contains(@class, 'button-children')]/span")).getText();
                infoList.add(new SingleData(text));
            } else {
                Logs.error("no matching input type");
            }
        }

        return infoList;
    }

    private boolean isNumeric(WebElement node) {
        return !node.findElements(By.xpath(".//input[@inputmode='numeric']")).isEmpty();
    }

    private boolean isCheckbox(WebElement node) {
        return !node.findElements(By.xpath(".//input[@type='checkbox']")).isEmpty();
    }

    private boolean isList(WebElement node) {
        return !node.findElements(By.xpath(".//span[@data-role='listbox']")).isEmpty();
    }

    @Override
    public void goToPage() {

    }
}
