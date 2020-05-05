package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ContactsPage extends BasePage {
    @FindBy(xpath = "//span[contains(text(), 'First Name')]")
    WebElement firstNameLabel;
    @FindBy(css = ".btn-primary")
    WebElement createContactButton;

    public ContactsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public ContactsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(firstNameLabel));
        return this;
    }

    @Step("Opening 'Contacts' page.")
    public ContactsPage openPage() {
        List<WebElement> menuButtons = driver.findElements(By.cssSelector(".navbar-link"));
        menuButtons.get(1).click();
        isPageOpened();
        return this;
    }

    @Step("Clicking 'Create contact' button")
    public CreateContactPage createContactButtonClick() {
        createContactButton.click();
        return new CreateContactPage(driver, wait);
    }
}
