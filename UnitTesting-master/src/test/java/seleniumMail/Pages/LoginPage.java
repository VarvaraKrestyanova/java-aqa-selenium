package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import seleniumMail.Helpers.WebDriverSingleton;

import java.util.NoSuchElementException;

public class LoginPage {

    private static By logInBtn = By.cssSelector(".button2_theme_mail-white");
    private static By welcomeLoginText = By.xpath("//span[@class='WelcomePage-tagline']");
    private static By loginField = By.cssSelector("#passp-field-login");
    private static By logInBtnOnForm = By.xpath("//button[@id='passp:sign-in']");
    private static By passwordField = By.cssSelector("#passp-field-passwd");

    private static WebDriver driver;

    public LoginPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
    }

    public LoginPage fillLogInForm(String username, String password) {
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(logInBtnOnForm).click();
        driver.findElement(passwordField).sendKeys(password);
        return new LoginPage();
    }

    public InboxPage logIn(String username, String password) {
        driver.findElement(logInBtn).click();
        fillLogInForm(username, password);
        driver.findElement(logInBtnOnForm).click();
        return new InboxPage();
    }

    public boolean isLoginPageOpened() {
        try {
            return driver.findElement(welcomeLoginText).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public void goToUrl(String url) {
        driver.navigate().to(url);
    }

}
