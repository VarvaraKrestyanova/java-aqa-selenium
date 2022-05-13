package seleniumMail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumEasyBootstrapDemoPage {

    private static By downloadBtn = By.cssSelector("#cricle-btn");
    private static By loadingPercentage = By.xpath("//div[@class='percenttext']");

    private static WebDriver driver;

    public SeleniumEasyBootstrapDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumEasyBootstrapDemoPage download() {
        driver.findElement(downloadBtn).click();
        return this;
    }

    public boolean waitForDownloadLimitAndCheckLimit(int limit) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        wait.pollingEvery(Duration.ofMillis(1000));
        WebElement percentageElement = driver.findElement(loadingPercentage);
        wait.until(driver  -> Integer.parseInt(percentageElement.getText().substring(0, percentageElement.getText().length() - 1)) >= limit);
        return getLoadingPercentage() >= limit;
    }

    public int getLoadingPercentage() {
        String percentageData = driver.findElement(loadingPercentage).getText();
        return Integer.parseInt(percentageData.substring(0, percentageData.length() - 1));
    }

    public void refreshPage() {
        driver.navigate().refresh();
    }

}
