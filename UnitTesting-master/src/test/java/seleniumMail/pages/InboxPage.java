package seleniumMail.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumMail.helpers.WebDriverSingleton;

import java.time.Duration;
import java.util.NoSuchElementException;

public class InboxPage {

    @FindBy(css = ".ns-view-right-box")
    WebElement rightBoxList;

    @FindBy(css = ".b-user")
    WebElement username;

    @FindBy(css = ".user-account_left-name")
    WebElement userIcon;

    @FindBy(xpath = "//span[.='Log out']")
    WebElement logoutBtn;

    private static WebDriver driver;

    public InboxPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    public boolean isRightBoxListDisplayed() {
        try {
            return rightBoxList.isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public InboxPage waitForUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1500));
        wait.until(ExpectedConditions.visibilityOf(username));
        return new InboxPage();
    }

    public LoginPage logoutFromInboxPage() {
        userIcon.click();
        logoutBtn.click();
        return new LoginPage();
    }

}
