package seleniumMail.helpers.reporting;

import com.google.common.collect.ImmutableMap;
import io.qameta.allure.Attachment;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import seleniumMail.helpers.WebDriverSingleton;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;

public class TestListener implements TestWatcher {

    private WebDriver driver;
    private Capabilities capabilities;

    public TestListener() {
        this.driver = WebDriverSingleton.getInstance().getDriver();
        this.capabilities = ((RemoteWebDriver) driver).getCapabilities();
    }

    @Override
    public void testFailed(ExtensionContext extensionContext, Throwable throwable) {
        screenshot();
        setAllureEnvironment(capabilities.getBrowserName(), capabilities.getBrowserVersion());
    }

    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    public void screenshot() {
        if (driver == null) {
            System.out.println("Driver for screenshot not found");
            return;
        }
        saveScreenshot(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
    }

    public void setAllureEnvironment(String browserName, String browserNumber) {
        allureEnvironmentWriter(
                ImmutableMap.<String, String>builder()
                        .put("Browser", browserName)
                        .put("Browser.Version", browserNumber)
                        .build());
    }

}
