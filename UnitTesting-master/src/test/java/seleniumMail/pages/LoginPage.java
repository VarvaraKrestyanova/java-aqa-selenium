package seleniumMail.pages;

import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumMail.helpers.WebDriverSingleton;

import java.io.File;
import java.io.IOException;
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

    private static RemoteWebDriver driver;

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

    //created additional same method with screenshot so that user has the ability which type of constructor he needs
    //and not to change the call to the method in other parts of the code
    public InboxPage logIn(String username, String password, String screenshotPath, String screenshotName) {
        driver.navigate().to("https://mail.yandex.com/");
        takeScreenshot(screenshotPath, screenshotName);
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

    public void takeScreenshot(String path, String fileName) {
        String randomValue = Faker.instance().code().toString();
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(String.format("%s%s_%s.png", path, fileName, randomValue)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
