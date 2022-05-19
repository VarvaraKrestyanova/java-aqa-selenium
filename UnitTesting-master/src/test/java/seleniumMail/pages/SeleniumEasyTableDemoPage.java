package seleniumMail.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import seleniumMail.helpers.User;

import java.util.ArrayList;
import java.util.List;

public class SeleniumEasyTableDemoPage {

    private static By numberOfEntriesDdl = By.cssSelector("[name='example_length']");
    private static By tableRow = By.xpath("//tbody//tr");
    private static By nextPaginationButtons = By.xpath("//a[@class= 'paginate_button ']");

    private static RemoteWebDriver driver;

    public SeleniumEasyTableDemoPage(RemoteWebDriver driver) {
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
        for (WebElement tableRow: tableRows) {
            String name = tableRow.findElement(By.xpath(String.format(".//td[1]"))).getText();
            String position = tableRow.findElement(By.xpath(String.format(".//td[2]"))).getText();
            String office = tableRow.findElement(By.xpath(String.format(".//td[3]"))).getText();
            int age = Integer.parseInt(tableRow.findElement(By.xpath(String.format(".//td[4]"))).getText());
            String salaryString = tableRow.findElement(By.xpath(String.format(".//td[6]"))).getText()
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