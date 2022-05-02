package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class InboxPage {

    private static By rightBoxList = By.cssSelector(".ns-view-right-box");
    private static By username = By.cssSelector(".b-user");

    @FindBy(css = ".user-account_left-name")
    WebElement userIcon;

    @FindBy(xpath = "//span[.='Log out']")
    WebElement logoutBtn;

    private static WebDriver driver;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isRightBoxListDisplayed() {
        try {
            return driver.findElement(rightBoxList).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

    public InboxPage waitForUsername() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(username));
        return new InboxPage(driver);
    }

    public InboxPage logoutFromInboxPage() {
        userIcon.click();
        logoutBtn.click();
        return new InboxPage(driver);
    }

}
