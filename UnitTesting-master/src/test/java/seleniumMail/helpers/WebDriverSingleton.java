package seleniumMail.helpers;

import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private RemoteWebDriver driver;
    private static WebDriverSingleton instance;
    private DesiredCapabilities desiredCapabilities;

    private static final String  SAURCELABS_USERNAME = "saucelabs.username";
    private static final String SAURCELABS_KEY = "saucelabs.key";
    private static final String saucelabsUsername = PropertiesUtil.get(SAURCELABS_USERNAME);
    private static final String saucelabsKey = PropertiesUtil.get(SAURCELABS_KEY);
    public static final String saucelabsURL = "https://" + saucelabsUsername + ":" + saucelabsKey + "@ondemand.saucelabs.com:443/wd/hub";

    private WebDriverSingleton() {}

    public static WebDriverSingleton getInstance() {

        if (instance == null) {
            instance = new WebDriverSingleton();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() {
        if (driver == null) {
            EdgeOptions browserOptions = new EdgeOptions();
            browserOptions.setPlatformName("Windows 10");
            browserOptions.setBrowserVersion("latest");
            Map<String, Object> sauceOptions = new HashMap<>();
            browserOptions.setCapability("sauce:options", sauceOptions);

            try {
                driver = new RemoteWebDriver(new URL(saucelabsURL), browserOptions);
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
