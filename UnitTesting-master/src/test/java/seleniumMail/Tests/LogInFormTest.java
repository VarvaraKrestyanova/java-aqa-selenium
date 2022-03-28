package seleniumMail.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import seleniumMail.Helpers.PropertiesUtil;
import seleniumMail.Pages.InboxPage;
import seleniumMail.Pages.LoginPage;
import static org.junit.jupiter.api.Assertions.*;

public class LogInFormTest {

    private static final String USERNAME = "login";
    private static final String PASSWORD = "password";
    private static final String YANDEX_MAIL_URL = "mail.url";

    private static String user = PropertiesUtil.get(USERNAME);
    private static String password = PropertiesUtil.get(PASSWORD);
    private static String url = PropertiesUtil.get(YANDEX_MAIL_URL);

    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @Test
    public void logInTest() {
        driver.navigate().to(url);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.logIn(user, password);
        InboxPage inboxPage = new InboxPage(driver);
        assertTrue(inboxPage.isRightBoxListDisplayed(), "Login with correct credentials failed as inbox does not display!");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }

}
