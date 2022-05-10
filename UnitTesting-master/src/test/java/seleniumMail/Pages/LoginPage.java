package seleniumMail.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumMail.Helpers.WebDriverSingleton;

import java.util.NoSuchElementException;

public class LoginPage {

    @FindBy(css = ".button2_theme_mail-white")
    WebElement logInBtn;
    @FindBy(xpath = "//span[@class='WelcomePage-tagline']")
    WebElement welcomeLoginText;

    @FindBy(css = "#passp-field-login")
    WebElement loginField;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    WebElement logInBtnOnForm;

    @FindBy(css = "#passp-field-passwd")
    WebElement passwordField;

    private static WebDriver driver;

    public LoginPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public LoginPage fillLogInForm(String username, String password) {
        loginField.sendKeys(username);
        logInBtnOnForm.click();
        passwordField.sendKeys(password);
        return new LoginPage();
    }

    public InboxPage logIn(String username, String password) {
        driver.navigate().to("https://mail.yandex.com/");
        logInBtn.click();
        fillLogInForm(username, password);
        logInBtnOnForm.click();
        return new InboxPage();
    }

    public boolean isLoginPageOpened() {
        try {
            return welcomeLoginText.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
