package seleniumMail.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import seleniumMail.helpers.PropertiesUtil;
import seleniumMail.helpers.WebDriverSingleton;
import seleniumMail.pages.InboxPage;
import seleniumMail.pages.LoginPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class LogInFormTest {

    private static final String USERNAME = "user1";
    private static final String PASSWORD = "password1";
    private static final String USERNAME2 = "user2";
    private static final String PASSWORD2 = "password2";

    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);
    private static String user2 = PropertiesUtil.get(USERNAME2);
    private static String password2 = PropertiesUtil.get(PASSWORD2);

    @Test
    public void logInTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(user, password);
        Thread.sleep(1000); //Explicit Wait type
        InboxPage inboxPage = new InboxPage();
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

    static Stream<Arguments> logInWithDifferentUsers() {
        return Stream.of(
                Arguments.arguments(user, password),
                Arguments.arguments(user2, password2));
    }

    @ParameterizedTest
    @MethodSource
    public void logInWithDifferentUsers(String name, String pwd) {
        LoginPage loginPage = new LoginPage();
        loginPage.logIn(name, pwd);
        InboxPage inboxPage = new InboxPage();
        inboxPage.waitForUsername();
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed!");
    }

    @AfterEach
    void cleanup() {
        WebDriverSingleton.getInstance().quitDriver();
    }

}
