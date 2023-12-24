package stocks.utilities;

import org.openqa.selenium.WebDriver;
import stocks.model.Data;
import stocks.model.ResultFilter;
import stocks.page.charts.AlertPanel;
import stocks.page.charts.BottomBar;
import stocks.page.charts.LeftBar;
import stocks.page.charts.RightBar;
import stocks.page.charts.TopBar;
import stocks.page.index.IndexPage;
import stocks.page.index.IndexTopBar;
import stocks.page.index.LoginPage;
import stocks.page.modals.AlertWindow;
import stocks.page.modals.IndicatorsWindow;
import stocks.page.modals.TechnicalStrategyWindow;

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
        new RightBar(driver).clickAlertButton();
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

        new TopBar(driver).waitPageToLoad();
    }

    public void setupPreconditions(int intervalTime, String strategyName) {
        selectInterval(intervalTime);
        deleteAlerts();
        selectStrategy(strategyName);
    }

    private void selectInterval(int time) {
        final var superChartTopBar = new TopBar(driver);
        superChartTopBar.selectInterval(time);
    }

    private void selectStrategy(String strategyName) {
        final var topBar = new TopBar(driver);
        topBar.waitPageToLoad();
        topBar.clickIndicatorsTab();

        final var indicatorsWindow = new IndicatorsWindow(driver);
        indicatorsWindow.waitPageToLoad();
        indicatorsWindow.selectStrategy(strategyName);
        indicatorsWindow.closeWindow();

        topBar.waitPageToLoad();
        AutomationUtils.automationSleep(500);

        final var superChartBottomBar = new BottomBar(driver);
        superChartBottomBar.clickSettingsButton();

        new TechnicalStrategyWindow(driver).waitPageToLoad();
    }

    private void deleteAlerts() {
        final var alertPanel = new AlertPanel(driver);

        if (!alertPanel.isOpen()) { //we only click if it is closed
            new RightBar(driver).clickAlertButton();
        }

        alertPanel.removeAllAlertsButton();
    }

    public void fillData(Data data, ResultFilter resultFilter) {
        Logs.info("Filling all data");
        final var technicalStrategy = new TechnicalStrategyWindow(driver);
        final var n = data.getListData().size();
        final var inputList = technicalStrategy.getWebElementInputList();

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
            final var superChartBottomBar = new BottomBar(driver);
            final var currentResult = superChartBottomBar.getCurrentResults();

            //only compares to the new one if is not null and is a wanted one according to the filters
            if (currentResult != null && resultFilter.isWantedResult(currentResult)) {
                Data.compareUpdate(currentResult, technicalStrategy);
            }
        }
    }


    public void alertBestResults() {
        final var technicalStrategy = new TechnicalStrategyWindow(driver);
        final var inputDataList = Data.getBestData().getListData(); //the combination of inputs
        final var webElementInputList = technicalStrategy.getWebElementInputList(); //the web elements
        final var n = inputDataList.size();

        for (var i = 0; i < n; i++) {
            final var currentData = inputDataList.get(i);
            final var currentElement = webElementInputList.get(i);

            Logs.debug("actual: %s", currentData);

            switch (currentData.getInputType()) {
                case NUMERIC -> technicalStrategy.fillNumericValue(currentData.getNumericValue(), currentElement);
                case LIST -> technicalStrategy.selectListELement(currentData.getStringValue(), currentElement);
                case CHECKBOX -> technicalStrategy.selectCheckboxValue(currentData.isBooleanValue(), currentElement);
            }

            AutomationUtils.automationSleep(500);
        }

        technicalStrategy.clickOnOk();

        new BottomBar(driver).clickClockButton();

        final var alertWindow = new AlertWindow(driver);
        alertWindow.waitPageToLoad();
        alertWindow.createAlert();
    }
}
