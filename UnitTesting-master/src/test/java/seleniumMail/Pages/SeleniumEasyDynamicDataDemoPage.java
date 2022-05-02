package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumEasyDynamicDataDemoPage {

    private static By newUserBtn = By.cssSelector("#save");
    private static By userIcon = By.xpath("//div[@id='loading']//img");
    private static By userName = By.xpath("//*[starts-with(text(), 'First Name : ')]");

    private static WebDriver driver;

    public SeleniumEasyDynamicDataDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isUserReady() {
        driver.findElement(newUserBtn).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(userIcon));
        return driver.findElement(userIcon).isDisplayed() & driver.findElement(userName).isDisplayed();
    }

}
