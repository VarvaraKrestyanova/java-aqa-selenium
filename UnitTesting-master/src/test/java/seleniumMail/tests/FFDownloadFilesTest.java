package seleniumMail.tests;

import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import seleniumMail.helpers.PropertiesUtil;

public class FFDownloadFilesTest {

    private static final String FILES_URL = "files.url";
    private static String filesUrl = PropertiesUtil.get(FILES_URL);

    private WebDriver driver;

    @BeforeEach
    void setup() {
        FirefoxDriverManager.getInstance().setup();
        driver = new FirefoxDriver();
    }

    @Test
    public void downloadFilesTest() {
        driver.get(filesUrl);
    }

    @AfterEach
    void cleanup() {
        driver.close();
        driver.quit();
    }

}
