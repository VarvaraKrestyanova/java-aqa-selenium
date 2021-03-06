package seleniumMail.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seleniumMail.Helpers.PropertiesUtil;
import seleniumMail.Helpers.WebDriverSingleton;
import seleniumMail.Pages.InboxPage;
import seleniumMail.Pages.LoginPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class YandexMailTest {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "password1";
    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);

    private LoginPage loginPage;

    @BeforeEach
    void setup() {
        loginPage = new LoginPage();
    }

    @Test
    public void logInTest() {
        InboxPage inboxPage = loginPage.logIn(user, password);
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

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
