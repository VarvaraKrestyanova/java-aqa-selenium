package seleniumMail.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SeleniumEasyDropdownDemoPage {

    String STATE_OPTION_BY_INDEX_XPATH_PATTERN = "//select[@id='multi-select']/option[%d]";

    private static By statesForm = By.cssSelector("#multi-select");
    private static By state = By.xpath("//select[@id='multi-select']/option");
    private static By displayedTextAfterSelect = By.xpath("//p[@class='getall-selected']");

    private static WebDriver driver;
    private Select selector;

    public SeleniumEasyDropdownDemoPage(WebDriver driver) {
        this.driver = driver;
    }

    public SeleniumEasyDropdownDemoPage selectStateByName(String stateName) {
        selector = new Select(driver.findElement(statesForm));
        selector.selectByVisibleText(stateName);
        return new SeleniumEasyDropdownDemoPage(driver);
    }

    public List<String> selectRandomStatesAndGetNames(int numberOfStates) {

        List<WebElement> statesElements = driver.findElements(state);
        Random random = new Random();
        List<String> expectedSelectedStates = new ArrayList<>();

        for (int i = 0; i < numberOfStates; i++) {
            int randomIndex = random.nextInt(statesElements.size());
            if (randomIndex == 0) {randomIndex += 1;}
            String xpath = String.format(STATE_OPTION_BY_INDEX_XPATH_PATTERN, randomIndex);
            WebElement element = driver.findElement(By.xpath(xpath));
            String stateName = element.getText();
            if (!expectedSelectedStates.contains(stateName)) {
                selectStateByName(stateName);
                expectedSelectedStates.add(stateName);
            } else if (i > 0 & expectedSelectedStates.contains(stateName)) {
                i -= 1;
            }
        }

        return expectedSelectedStates;
    }

    public String getDisplayedTextWithChosenStates() {
        return driver.findElement(displayedTextAfterSelect).getText();
    }

   public List<String> getSelectedStates() {
       return selector.getAllSelectedOptions().stream().map(WebElement::getText).collect(Collectors.toList());
   }

}
