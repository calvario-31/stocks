package stocks.utilities;

import org.openqa.selenium.WebDriver;
import stocks.model.Data;
import stocks.model.ResultFilter;
import stocks.page.charts.IndicatorsWindow;
import stocks.page.charts.LeftBar;
import stocks.page.charts.SuperChartBottomBar;
import stocks.page.charts.SuperChartTopBar;
import stocks.page.login.IndexPage;
import stocks.page.login.IndexTopBar;
import stocks.page.login.LoginPage;
import stocks.page.technical.TechnicalStrategy;

public class Flows {
    private final WebDriver driver;

    public Flows(WebDriver driver) {
        this.driver = driver;
    }

    public void goToHomePage(String username, String password) {
        goToIndex();
        login(username, password);
        navigateChartPage();
        new LeftBar(driver).removingIndicators();
    }

    public void goToIndex() {
        new IndexPage(driver).goToPage();
    }

    public void login(String username, String password) {
        final var indexPage = new IndexPage(driver);
        indexPage.waitPageToLoad();
        new IndexPage(driver).goToLogin();

        final var loginPage = new LoginPage(driver);
        loginPage.waitPageToLoad();
        loginPage.signIn(username, password);
        indexPage.waitPageToLoad();
    }

    public void navigateChartPage() {
        final var indexTopBar = new IndexTopBar(driver);
        indexTopBar.goToSuperChart();

        new SuperChartTopBar(driver).waitPageToLoad();
    }

    public void selectInterval(int time) {
        final var superChartTopBar = new SuperChartTopBar(driver);
        superChartTopBar.selectInterval(time);
    }

    public void selectStrategy(String strategyName) {
        final var topBar = new SuperChartTopBar(driver);
        topBar.waitPageToLoad();
        topBar.clickIndicatorsTab();

        final var indicatorsWindow = new IndicatorsWindow(driver);
        indicatorsWindow.waitPageToLoad();
        indicatorsWindow.selectStrategy(strategyName);
        indicatorsWindow.closeWindow();

        topBar.waitPageToLoad();
        AutomationUtils.automationSleep(500);

        final var superChartBottomBar = new SuperChartBottomBar(driver);
        superChartBottomBar.clickSettingsButton();

        new TechnicalStrategy(driver).waitPageToLoad();
    }

    public void fillData(Data data, ResultFilter resultFilter) {
        final var technicalStrategy = new TechnicalStrategy(driver);
        final var n = data.getListData().size();
        final var inputList = technicalStrategy.getInputData();

        Logs.debug("Filling data with size: %d", n);

        for (var i = 0; i < n; i++) {
            final var currentData = data.getListData().get(i);
            final var currentElement = inputList.get(i);

            Logs.debug("actual: %s", currentData);

            switch (currentData.getInputType()) {
                case NUMERIC -> technicalStrategy.fillNumericValue(currentData.getNumericValue(), currentElement);
                case LIST -> technicalStrategy.selectListELement(currentData.getStringValue(), currentElement);
                case CHECKBOX -> technicalStrategy.selectCheckboxValue(currentData.isBooleanValue(), currentElement);
            }

            AutomationUtils.automationSleep(1000);

            Logs.debug("Gathering results");
            final var superChartBottomBar = new SuperChartBottomBar(driver);
            final var currentResult = superChartBottomBar.getCurrentResults();

            //only compares to the new one if is not null and is a wanted one according to the filters
            if (currentResult != null && resultFilter.isWantedResult(currentResult)) {
                Data.compareUpdate(currentResult, technicalStrategy);
            }
        }
    }
}
