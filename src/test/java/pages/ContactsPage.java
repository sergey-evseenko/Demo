package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ContactsPage extends BasePage {
    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    WebElement createContactButton;
    @FindBy(name = "query")
    WebElement searchInput;

    public ContactsPage(WebDriver driver) {
        super(driver);
    }

    public ContactsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(createContactButton));
        return this;
    }

    @Step("Opening 'Contacts' page.")
    public ContactsPage openPage() {
        /*List<WebElement> menuButtons = driver.findElements(By.cssSelector(".navbar-link"));
        menuButtons.get(1).click();
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        return this;*/

        driver.get("https://sell.qa.inperium.dev/contacts");
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Contacts' page was success opened.");
        return this;
    }

    @Step("Clicking 'Create contact' button")
    public CreateContactPage createContactButtonClick() {
        createContactButton.click();
        log.info("'Create contact' button was clicked.");
        return new CreateContactPage(driver);
    }

    @Step("Searching and opening contact.")
    public ContactDetailsPage searchAndOpenContact(Contact contact) {
        String locator = "//span[contains(text(),'%s')]";
        searchInput.sendKeys(contact.getFirstName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, contact.getFirstName()))));
        AllureUtils.takeScreenshot(driver);
        log.info("Contact with first name: " + contact.getFirstName() + " was found.");
        driver.findElement(By.xpath(String.format(locator, contact.getFirstName()))).click();
        return new ContactDetailsPage(driver);
    }

    @Step("Verifying that contact was successfully deleted")
    public ContactsPage verifyThatContactWasDeleted(Contact contact) {
        String locator = "//span[contains(text(),'%s')]";
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Contacts' page was opened.");
        searchInput.sendKeys(contact.getFirstName());
        sleep();
        sleep();
        List<WebElement> deletedContact = driver.findElements(By.xpath(String.format(locator, contact.getFirstName())));
        assertEquals(deletedContact.size(), 0, "Contact deleting updating Error.");
        AllureUtils.takeScreenshot(driver);
        log.info("Company with first name: " + contact.getFirstName() + " was success deleted.");
        return this;
    }
}
