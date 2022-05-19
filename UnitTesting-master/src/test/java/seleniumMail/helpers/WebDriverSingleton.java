package seleniumMail.helpers;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private WebDriver driver;
    private static WebDriverSingleton instance;
    private DesiredCapabilities desiredCapabilities;

    private WebDriverSingleton() {}

    public static WebDriverSingleton getInstance() {
        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            desiredCapabilities.setBrowserName("chrome");
            desiredCapabilities.setPlatform(Platform.WIN10);
            try {
                driver = new RemoteWebDriver(new URL("http://192.168.100.25:5555"), desiredCapabilities);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        return driver;
    }

    public void quitDriver() {
        driver.quit();
        driver = null;
    }

}
