package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import seleniumMail.Helpers.User;

import java.util.ArrayList;
import java.util.List;

public class SeleniumEasyTableDemoPage {

    private static By numberOfEntriesDdl = By.cssSelector("[name='example_length']");
    private static By tableRow = By.xpath("//tbody//tr");
    private static By nextPaginationButtons = By.xpath("//a[@class= 'paginate_button ']");

    private static WebDriver driver;

    public SeleniumEasyTableDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumEasyTableDemoPage setEntriesNumberToShow(int entriesNumber) {
        Select numberOfEntriesSelector = new Select(driver.findElement(numberOfEntriesDdl));
        numberOfEntriesSelector.selectByVisibleText(String.valueOf(entriesNumber));
        return this;
    }

    public int getNumberOfRows() {
        List<WebElement> tableRows = driver.findElements(tableRow);
        return  tableRows.size();
    }

    public List<User> getCustomObjects(int startAge, int maxSalary) {
        List<User> users = new ArrayList<>();
        List<WebElement> tableRows = driver.findElements(tableRow);
        WebElement tableBlock = driver.findElement(By.xpath("//tbody"));
        for (WebElement tableRow: tableRows) {
            int index = tableRows.indexOf(tableRow) + 1;
            String name = tableBlock.findElement(By.xpath(String.format(".//tr[%d]//td[1]", index))).getText();
            String position = tableBlock.findElement(By.xpath(String.format(".//tr[%d]//td[2]", index))).getText();
            String office = tableBlock.findElement(By.xpath(String.format(".//tr[%d]//td[3]", index))).getText();
            int age = Integer.parseInt(tableBlock.findElement(By.xpath(String.format(".//tr[%d]//td[4]", index))).getText());
            String salaryString = tableBlock.findElement(By.xpath(String.format(".//tr[%d]//td[6]", index))).getText()
                    .replace("$", "")
                    .replace(",", "")
                    .replace("/y", "");
            int salary = Integer.parseInt(salaryString);

            if (age > startAge & salary <= maxSalary) {
                users.add(new User(name, position, office));
            }
        }
        return users;
    }

    public int countPageNumbers() {
        return driver.findElements(nextPaginationButtons).size() + 1;
    }

    public List<User> getCustomObjectsOnAllPages(int startAge, int maxSalary) {
        List<User> users = new ArrayList<>();
        users.addAll(getCustomObjects(startAge, maxSalary));
        int pageNumbers = countPageNumbers();
        for (int i = 0; i < pageNumbers - 1; i++) {
            driver.findElements(nextPaginationButtons).get(i).click();
            users.addAll(getCustomObjects(startAge, maxSalary));
        }
        return users;
    }

}