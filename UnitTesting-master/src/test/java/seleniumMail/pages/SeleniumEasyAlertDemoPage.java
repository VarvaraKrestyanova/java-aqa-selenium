package seleniumMail.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumEasyAlertDemoPage {

    private static By confirmBoxBtn = By.cssSelector("[onclick='myConfirmFunction()']");
    private static By confirmBoxResult = By.cssSelector("#confirm-demo");
    private static By promptBoxBtn = By.xpath("//button[.='Click for Prompt Box']");
    private static By promptBoxResult = By.cssSelector("#prompt-demo");
    private static By jsAlertBtn = By.xpath("//button[@class='btn btn-default']");

    private static RemoteWebDriver driver;
    private Alert alert;

    public SeleniumEasyAlertDemoPage(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public SeleniumEasyAlertDemoPage openConfirmBoxAlert() {
        driver.findElement(confirmBoxBtn).click();
        return new SeleniumEasyAlertDemoPage(driver);
    }

    public SeleniumEasyAlertDemoPage cancelConfirmBoxAlert() {
        alert = driver.switchTo().alert();
        alert.dismiss();
        return new SeleniumEasyAlertDemoPage(driver);
    }

    public SeleniumEasyAlertDemoPage acceptAlert() {
        alert = driver.switchTo().alert();
        alert.accept();
        return new SeleniumEasyAlertDemoPage(driver);
    }

    public String getConfirmBoxResult() {
        return driver.findElement(confirmBoxResult).getText();
    }

    public SeleniumEasyAlertDemoPage openJSAlertBox() {
        driver.findElement(jsAlertBtn).click();
        return new SeleniumEasyAlertDemoPage(driver);
    }

    public String getJSAlertText() {
        alert = driver.switchTo().alert();
        return alert.getText();
    }





}

