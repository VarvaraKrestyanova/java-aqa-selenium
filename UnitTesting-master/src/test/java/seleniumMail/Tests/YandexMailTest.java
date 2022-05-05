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
    private static final String YANDEX_MAIL_URL = "mail.url";

    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);
    private static String url = PropertiesUtil.get(YANDEX_MAIL_URL);

    @Test
    public void logInTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.goToUrl(url);
        loginPage.logIn(user, password);
        InboxPage inboxPage = new InboxPage();
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

    @Test
    public void logOutTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.goToUrl(url);
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
