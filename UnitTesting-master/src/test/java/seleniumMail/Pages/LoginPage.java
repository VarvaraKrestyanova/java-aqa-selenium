package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static By logInBtn = By.cssSelector(".button2_theme_mail-white");
    private static By loginField = By.cssSelector("#passp-field-login");
    private static By logInBtnOnForm = By.xpath("//button[@id='passp:sign-in']");
    private static By passwordField = By.cssSelector("#passp-field-passwd");

    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage fillLogInForm(String username, String password) {
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(logInBtnOnForm).click();
        driver.findElement(passwordField).sendKeys(password);
        return new LoginPage(driver);
    }

    public LoginPage logIn(String username, String password) {
        driver.findElement(logInBtn).click();
        fillLogInForm(username, password);
        driver.findElement(logInBtnOnForm).click();
        return new LoginPage(driver);
    }

}
