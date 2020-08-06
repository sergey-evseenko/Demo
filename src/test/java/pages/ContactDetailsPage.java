package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;

@Log4j2
public class ContactDetailsPage extends BasePage {

    @FindBy(css = ".NoteForm_note__form__JsmUJ")
    WebElement actionsMenu;
    @FindBy(name = "firstName")
    WebElement inputFirstName;
    @FindBy(name = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    WebElement saveButton;
    @FindBy(xpath = "//p[text()='Company Created']")
    WebElement contactCreated;
    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    WebElement deleteButton;

    public ContactDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ContactDetailsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(contactCreated));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Verifying that contact was correctly created.")
    public void verifyThatContactWasCreated(Contact contact) {
        sleep();
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Contact details' page was success opened.");
        String actualFirstName = inputFirstName.getAttribute("value");
        assertEquals(actualFirstName, contact.getFirstName(), "Contact creation error. Contact first name.");
        String actualLastName = inputLastName.getAttribute("value");
        assertEquals(actualLastName, contact.getLastName(), "Contact creation error. Contact last name.");
        log.info("Contact was successfully created. Contact first name: " + contact.getFirstName());
    }

    @Step("Updating and saving contact.")
    public ContactDetailsPage updateAndSaveContact(Contact updatedContact) {
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        inputFirstName.clear();
        inputFirstName.sendKeys(updatedContact.getFirstName());
        inputLastName.clear();
        inputLastName.sendKeys(updatedContact.getLastName());
        saveButton.click();
        return this;
    }

    @Step("Verifying that contact was updated")
    public void verifyUpdatedContact(Contact updatedContact) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        String actualFirstName = inputFirstName.getAttribute("value");
        assertEquals(actualFirstName, updatedContact.getFirstName(), "Contact updating error. Contact first name.");
        String actualLastName = inputLastName.getAttribute("value");
        assertEquals(actualLastName, updatedContact.getLastName(), "Contact updating error. Contact last name.");
    }

    @Step("Deleting contact")
    public ContactsPage deleteContact() {
        wait.until(ExpectedConditions.visibilityOf(inputFirstName));
        contactCreated.click();
        deleteButton.click();
        return new ContactsPage(driver, wait);
    }
}
