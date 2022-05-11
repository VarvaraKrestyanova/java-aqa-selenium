package seleniumMail.tests;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import seleniumMail.pages.FileExamplesPage;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ChromeDownloadFilesTest {

    private WebDriver driver;
    private String targetDirectory;

    @BeforeEach
    void setup() {
        ChromeDriverManager.getInstance().setup();
        ChromeOptions chromeOptions = new ChromeOptions();

        String initialDirectory = System.getProperty("user.dir");
        targetDirectory = initialDirectory + "\\target\\files";

        Map<String, Object> prefs = new HashMap<String, Object>();
        if (!(new File(targetDirectory).exists())) {
            new File(targetDirectory).mkdirs();
        }
        prefs.put("download.default_directory", targetDirectory);
        chromeOptions.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void downloadFilesTest() {
        FileExamplesPage fileExamplesPage = new FileExamplesPage(driver);
        fileExamplesPage.downloadFileWithType("DOCX", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-docx-file-for-testing"));
        fileExamplesPage.downloadFileWithType("Excel", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-xls-file-for-testing"));
        fileExamplesPage.downloadFileWithType("PDF", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-pdf-file"));
    }

    @AfterEach
    void cleanup() {
        driver.close();
        driver.quit();
        new File(targetDirectory).delete();
    }

}
