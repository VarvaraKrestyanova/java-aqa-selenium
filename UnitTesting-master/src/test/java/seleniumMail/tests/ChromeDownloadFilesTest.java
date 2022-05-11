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

        String initialDirectory = System.getProperty("user.dir");
        targetDirectory = initialDirectory + "\\target\\files";

        ChromeOptions chromeOptions = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<String, Object>();
        if (!(new File(targetDirectory).exists())) {
            new File(targetDirectory).mkdirs();
        }
        prefs.put("plugins.plugins_disabled", new String[] {
                "Chrome PDF Viewer"
        });
        prefs.put("download.default_directory", targetDirectory);
        prefs.put("plugins.plugins_disabled", new String[] {
                "Chrome PDF Viewer"
        });
        prefs.put("plugins.always_open_pdf_externally", true);

        chromeOptions.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void downloadFilesTest() {
        FileExamplesPage fileExamplesPage = new FileExamplesPage(driver);
        fileExamplesPage.downloadFileWithType("DOCX", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-docx-file-for-testing.docx"), "File downloading is not successful");
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-doc-file-for-testing-1.doc"), "File downloading is not successful");
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "15-MB-docx-file-download.docx"), "File downloading is not successful");
        fileExamplesPage.downloadFileWithType("Excel", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-xls-file-for-testing.xls"), "File downloading is not successful");
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-xlsx-file-for-testing.xlsx"), "File downloading is not successful");
        fileExamplesPage.downloadFileWithType("PDF", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-pdf-file.pdf"), "File downloading is not successful");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
        new File(targetDirectory).delete();
    }

}
