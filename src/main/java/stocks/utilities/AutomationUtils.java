package stocks.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationUtils {
    public static void automationSleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException interruptedException) {
            System.err.println(interruptedException.getLocalizedMessage());
        }
    }

    public static WebDriver setupDriver() {
        Logs.debug("Setting driver");
        WebDriverManager.chromedriver().setup();
        final var driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static void killDriver(WebDriver driver) {
        Logs.debug("Killing driver");
        driver.quit();
    }
}
