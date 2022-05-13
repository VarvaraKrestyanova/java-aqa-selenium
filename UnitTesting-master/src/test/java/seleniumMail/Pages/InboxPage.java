package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumMail.Helpers.WebDriverSingleton;

import java.time.Duration;
import java.util.NoSuchElementException;

public class InboxPage {

    private static By rightBoxList = By.cssSelector(".ns-view-right-box");
    private static By username = By.cssSelector(".b-user");
    private static By userIcon = By.cssSelector(".user-account_left-name");
    private static By logoutBtn = By.xpath("//span[.='Log out']");

    private static WebDriver driver;

    public InboxPage() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
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
        return new InboxPage();
    }

    public LoginPage logoutFromInboxPage() {
        driver.findElement(userIcon).click();
        driver.findElement(logoutBtn).click();
        return new LoginPage();
    }

}
