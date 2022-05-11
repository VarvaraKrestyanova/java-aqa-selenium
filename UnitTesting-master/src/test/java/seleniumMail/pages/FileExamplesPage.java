package seleniumMail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import seleniumMail.helpers.PropertiesUtil;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class FileExamplesPage {

    String FILE_TYPE_DDL_BTN_XPATH_PATTERN = "//a[.='%s Files']";

    @FindBy(xpath = "//span[.='Sample']")
    WebElement sampleBtn;

    @FindBy(xpath = "//a[.='Documents']")
    WebElement documentsDdlValue;

    @FindBy(xpath = "//a[.='Download']")
    List<WebElement> downloadBtnList;

    @FindBy(xpath = "//span[@class='ezmob-footer-close']")
    WebElement closePopupBlockingBtn;

    private static final String FILES_URL = "files.url";
    private static final String filesUrl = PropertiesUtil.get(FILES_URL);

    private static WebDriver driver;

    public FileExamplesPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     *
     * @param fileType: PDF, DOCX, Excel, PPT, XML, Text, RTF, OTT, ODS, ODT, ODP
     */
    public void downloadFileWithType(String fileType, String filePath) {
        driver.get(filesUrl);
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);
        actions.moveToElement(sampleBtn).perform();
        actions.moveToElement(documentsDdlValue).perform();
        String xpathFileTypeDdl = String.format(FILE_TYPE_DDL_BTN_XPATH_PATTERN, fileType);
        WebElement fileTypeDdl = driver.findElement(By.xpath(xpathFileTypeDdl));
        fileTypeDdl.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5), Duration.ofMillis(1500));
        closePopupBlockingBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(downloadBtnList.get(0)));
        downloadBtnList.stream().forEach(WebElement::click);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isFileExists(String filePath, String fileName) {
        File file = new File(String.format("%s%s%s", filePath, File.separator, fileName));
        return file.exists();
    }








}
