package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.NoSuchElementException;

public class LoginPage {

    @FindBy(css = ".button2_theme_mail-white")
    WebElement logInBtn;
    @FindBy(xpath = "//span[@class='WelcomePage-tagline']")
    WebElement welcomeLoginText;
    private static By loginField = By.cssSelector("#passp-field-login");
    private static By logInBtnOnForm = By.xpath("//button[@id='passp:sign-in']");
    private static By passwordField = By.cssSelector("#passp-field-passwd");

    private static WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public LoginPage fillLogInForm(String username, String password) {
        driver.findElement(loginField).sendKeys(username);
        driver.findElement(logInBtnOnForm).click();
        driver.findElement(passwordField).sendKeys(password);
        return new LoginPage(driver);
    }

    public LoginPage logIn(String username, String password) {
        logInBtn.click();
        fillLogInForm(username, password);
        driver.findElement(logInBtnOnForm).click();
        return new LoginPage(driver);
    }

    public boolean isLoginPageOpened() {
        try {
            return welcomeLoginText.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
