package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class InboxPage {

    private static By rightBoxList = By.cssSelector(".ns-view-right-box");

    private static WebDriver driver;

    public InboxPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isRightBoxListDisplayed() {
        try {
            return driver.findElement(rightBoxList).isDisplayed();
        } catch (NoSuchElementException exception) {
            return false;
        }
    }

}
