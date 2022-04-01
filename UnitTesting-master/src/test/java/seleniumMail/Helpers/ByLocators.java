package seleniumMail.Helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ByLocators {

    private static WebDriver driver;

    private static By loginField = By.cssSelector("#passp-field-login");
    private static By logInBtnOnForm = By.xpath("//button[@id='passp:sign-in']");
    private static By yandexIcon = By.className("Header");
    private static By authForm = By.tagName("form");
    private static By forgotPasswordLink = By.linkText("Forgot your password?");
    private static By inputFieldOnAuthForm = By.id("passp-field-login");
    private static By learnMoreLink = By.partialLinkText("more");
    private static By cleanBth = By.name("clean");

}
