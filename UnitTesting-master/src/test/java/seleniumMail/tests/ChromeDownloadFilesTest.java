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
import java.util.Arrays;
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
        for (String s : Arrays.asList("sample-docx-file-for-testing.docx", "sample-doc-file-for-testing-1.doc", "15-MB-docx-file-download.docx")) {
            assertTrue(fileExamplesPage.isFileExists(targetDirectory, s), "File downloading is not successful");
        }
        fileExamplesPage.downloadFileWithType("Excel", targetDirectory);
        for (String s : Arrays.asList("sample-xls-file-for-testing.xls", "sample-xlsx-file-for-testing.xlsx")) {
            assertTrue(fileExamplesPage.isFileExists(targetDirectory, s), "File downloading is not successful");
        }
        fileExamplesPage.downloadFileWithType("PDF", targetDirectory);
        assertTrue(fileExamplesPage.isFileExists(targetDirectory, "sample-pdf-file.pdf"), "File downloading is not successful");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
        new File(targetDirectory).delete();
    }

}
