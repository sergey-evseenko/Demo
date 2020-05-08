package pages;

import io.qameta.allure.Step;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import java.util.List;

public class ContactsPage extends BasePage {
    @FindBy(xpath = "//span[contains(text(), 'First Name')]")
    WebElement firstNameLabel;
    @FindBy(xpath = "//div[contains(text(), 'Create')]")
    WebElement createContactButton;
    @FindBy(name = "query")
    WebElement searchInput;

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
        AllureUtils.takeScreenshot(driver);
        return this;
    }

    @Step("Clicking 'Create contact' button")
    public CreateContactPage createContactButtonClick() {
        createContactButton.click();
        return new CreateContactPage(driver, wait);
    }

    @Step("Searching and opening contact.")
    public ContactDetailsPage searchAndOpenContact(Contact contact) {
        String locator = "//div[contains(text(),'%s')]";
        searchInput.sendKeys(contact.getFirstName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, contact.getFirstName()))));
        driver.findElement(By.xpath(String.format(locator, contact.getFirstName()))).click();
        return new ContactDetailsPage(driver, wait);
    }

    @Step("Verifying that contact was successfully deleted")
    public void verifyThatContactWasDeleted(Contact updatedContact) {
        //TBD
    }
}
