package seleniumMail.tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import seleniumMail.helpers.PropertiesUtil;
import seleniumMail.helpers.WebDriverSingleton;
import seleniumMail.helpers.reporting.TestListener;
import seleniumMail.pages.InboxPage;
import seleniumMail.pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(TestListener.class)

public class YandexMailTest {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "password1";
    private static final String SCREENSHOT_PATH = "screenshots.path";

    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);

    private static String screenshotPath = PropertiesUtil.get(SCREENSHOT_PATH);

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();
    }

    @DisplayName("Login Test")
    @AllureId("Test01")
    @Description("Login test for Yandex")
    @Test
    public void logInTest() {
        InboxPage inboxPage = loginPage.logIn(user, password, screenshotPath, "testVar");
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

    @DisplayName("Logout Test")
    @AllureId("Test02")
    @Description("Logout test for Yandex")
    @Test
    public void logOutTest() {
        InboxPage inboxPage = loginPage.logIn(user, password);
        inboxPage.logoutFromInboxPage();
        assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened after logout!");
    }

    @AfterEach
    void cleanup() {
        WebDriverSingleton.getInstance().quitDriver();
    }

}
