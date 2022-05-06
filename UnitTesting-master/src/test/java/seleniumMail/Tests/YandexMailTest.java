package seleniumMail.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import seleniumMail.Helpers.PropertiesUtil;
import seleniumMail.Helpers.WebDriverSingleton;
import seleniumMail.Pages.InboxPage;
import seleniumMail.Pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexMailTest {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "password1";
    private static final String SCREENSHOT_PATH = "screenshots.path";

    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);
    private static String screenshotPath = PropertiesUtil.get(SCREENSHOT_PATH);

    @Test
    public void logInTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(user, password, screenshotPath, "testVar");
        InboxPage inboxPage = new InboxPage();
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

    @Test
    public void logOutTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(user, password);
        InboxPage inboxPage = new InboxPage();
        inboxPage.logoutFromInboxPage();
        assertTrue(loginPage.isLoginPageOpened(), "Login page is not opened after logout!");
    }

    @AfterEach
    void cleanup() {
        WebDriverSingleton.getInstance().quitDriver();
    }

}
