package seleniumMail.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import seleniumMail.helpers.PropertiesUtil;
import seleniumMail.helpers.User;
import seleniumMail.pages.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

public class SeleniumEasyTest {

    private static final String SELENIUM_EASY_DDL_URL = "multiselect.url";
    private static final String SELENIUM_EASY_ALERT_URL = "alert.url";
    private static final String SELENIUM_EASY_DYNAMIC_DATA_URL = "dynamicData.url";
    private static final String SELENIUM_EASY_BOOTSTRAP_URL = "bootstrap.url";
    private static final String SELENIUM_EASY_TABLE_URL = "table.url";
    private static String multiselectDemoUrl = PropertiesUtil.get(SELENIUM_EASY_DDL_URL);
    private static String alertDemoUrl = PropertiesUtil.get(SELENIUM_EASY_ALERT_URL);
    private static String dynamicDataDemoUrl = PropertiesUtil.get(SELENIUM_EASY_DYNAMIC_DATA_URL);
    private static String bootstrapDemoUrl = PropertiesUtil.get(SELENIUM_EASY_BOOTSTRAP_URL);
    private static String tableDemoUrl = PropertiesUtil.get(SELENIUM_EASY_TABLE_URL);

    private RemoteWebDriver driver;

    @BeforeEach
    void setup() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("chrome");
        desiredCapabilities.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL("http://192.168.100.25:4444/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Task 5: MultiSelect")
    public void multiselectTest() {
        driver.navigate().to(multiselectDemoUrl);
        SeleniumEasyDropdownDemoPage seleniumEasyDropdownDemoPage = new SeleniumEasyDropdownDemoPage(driver);

        List<String> selectedStates = seleniumEasyDropdownDemoPage.selectRandomStatesAndGetNames(3);
        List<String> actualSelectedStates = seleniumEasyDropdownDemoPage.getSelectedStates();
        assertEquals(selectedStates.size(), actualSelectedStates.size(), "Selected states number is wrong.");
        for (String selectedState: selectedStates) {
            assertTrue(actualSelectedStates.contains(selectedState));
        }
    }

    @Test
    @DisplayName("Task 6: Java Script Confirm Box 1")
    public void confirmConfirmBoxTest() {
        driver.navigate().to(alertDemoUrl);
        SeleniumEasyAlertDemoPage seleniumEasyAlertDemoPage = new SeleniumEasyAlertDemoPage(driver);
        seleniumEasyAlertDemoPage.openConfirmBoxAlert();
        seleniumEasyAlertDemoPage.acceptAlert();
        assertTrue(seleniumEasyAlertDemoPage.getConfirmBoxResult().equals("You pressed OK!"), "Result is wrong");
    }

    @Test
    @DisplayName("Task 6: Java Script Confirm Box 2")
    public void cancelConfirmBoxTest() {
        driver.navigate().to(alertDemoUrl);
        SeleniumEasyAlertDemoPage seleniumEasyAlertDemoPage = new SeleniumEasyAlertDemoPage(driver);
        seleniumEasyAlertDemoPage.openConfirmBoxAlert();
        seleniumEasyAlertDemoPage.cancelConfirmBoxAlert();
        assertTrue(seleniumEasyAlertDemoPage.getConfirmBoxResult().equals("You pressed Cancel!"), "Result is wrong");
    }

    @Test
    @DisplayName("Task 6: Java Script Alert Box")
    public void jsAlertDataTest() {
        driver.navigate().to(alertDemoUrl);
        SeleniumEasyAlertDemoPage seleniumEasyAlertDemoPage = new SeleniumEasyAlertDemoPage(driver);
        seleniumEasyAlertDemoPage.openJSAlertBox();
        assertTrue(seleniumEasyAlertDemoPage.getJSAlertText().equals("I am an alert box!"), "JS Alert Text is wrong");
        seleniumEasyAlertDemoPage.acceptAlert();
    }

    @Test
    @DisplayName("Task 7: Load User")
    public void userLoadingTest() {
        driver.navigate().to(dynamicDataDemoUrl);
        SeleniumEasyDynamicDataDemoPage seleniumEasyDynamicDataDemoPage = new SeleniumEasyDynamicDataDemoPage(driver);
        assertTrue(seleniumEasyDynamicDataDemoPage.isUserReady(), "User is not ready");
    }

    @Test
    @DisplayName("Task 8: Bootstrap")
    public void refreshPageTest() {
        driver.navigate().to(bootstrapDemoUrl);
        SeleniumEasyBootstrapDemoPage seleniumEasyBootstrapDemoPage = new SeleniumEasyBootstrapDemoPage(driver);
        seleniumEasyBootstrapDemoPage.download();
        assertTrue(seleniumEasyBootstrapDemoPage.waitForDownloadLimitAndCheckLimit(50), "Percentage limit is not >= 50");
        seleniumEasyBootstrapDemoPage.refreshPage();
        assertEquals(seleniumEasyBootstrapDemoPage.getLoadingPercentage(), 0, "Page is not refreshed");
    }

    @Test
    @DisplayName("Task 9: Table Sort")
    public void tableSortTest() {
        driver.navigate().to(tableDemoUrl);
        SeleniumEasyTableDemoPage seleniumEasyTableDemoPage = new SeleniumEasyTableDemoPage(driver);
        seleniumEasyTableDemoPage.setEntriesNumberToShow(10);
        assertEquals(seleniumEasyTableDemoPage.getNumberOfRows(), 10, "Number of Rows is wrong");

        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(new User("A. Cox", "Junior Technical Author", "San Francisco"));
        expectedUsers.add(new User("B. Williamson", "Integration Specialist", "New York"));
        expectedUsers.add(new User("G. Winters", "Accountant", "Tokyo"));
        expectedUsers.add(new User("M. Silva", "Marketing Designer", "London"));
        expectedUsers.add(new User("T. Nixon", "System Architect", "Edinburgh"));

        List<User> actualUsers = seleniumEasyTableDemoPage.getCustomObjectsOnAllPages(60, 500000);

        assertEquals(expectedUsers.toString(), actualUsers.toString(), "Filtered users are wrong");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }

}
