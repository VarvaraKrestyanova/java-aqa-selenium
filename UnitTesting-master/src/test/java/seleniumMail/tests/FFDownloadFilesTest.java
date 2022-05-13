package seleniumMail.tests;

import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import seleniumMail.helpers.PropertiesUtil;
import seleniumMail.pages.FileExamplesPage;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FFDownloadFilesTest {

    private WebDriver driver;
    private String targetDirectory;

    @BeforeEach
    void setup() {
        FirefoxDriverManager.getInstance().setup();

        String initialDirectory = System.getProperty("user.dir");
        targetDirectory = initialDirectory + "\\target\\files";
        if (!(new File(targetDirectory).exists())) {
            new File(targetDirectory).mkdirs();
        }

        FirefoxOptions options = new FirefoxOptions();

        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", targetDirectory);
        options.addPreference("browser.download.manager.showWhenStarting", false);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, text/csv");
        options.addPreference("pdfjs.disabled", true);

        driver = new FirefoxDriver(options);
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
