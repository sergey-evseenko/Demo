package pages;

import io.qameta.allure.Step;
import models.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class ContactDetailsPage extends BasePage {

    @FindBy(css = ".NoteForm_note__form__JsmUJ")
    WebElement actionsMenu;
    @FindBy(name = "firstName")
    WebElement inputFirstName;
    @FindBy(name = "lastName")
    WebElement inputLastName;
    @FindBy(xpath = "//button[contains(text(), 'Save')]")
    WebElement saveButton;
    @FindBy(xpath = "//button[contains(text(), 'Actions')]")
    WebElement actionsButton;
    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    WebElement deleteButton;

    public ContactDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public ContactDetailsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(actionsMenu));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Verifying that contacts details page was opened.")
    public void verifyThatContactWasCreated() {
        isPageOpened();
    }

    @Step("Updating and saving contact.")
    public ContactDetailsPage updateAndSaveContact(Contact updatedContact) {
        isPageOpened();
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
        assertEquals(actualFirstName, updatedContact.getFirstName(), "Company name updating Error.");
        String actualLastName = inputLastName.getAttribute("value");
        assertEquals(actualLastName, updatedContact.getLastName(), "Company domain updating Error.");
    }

    @Step("Deleting contact")
    public ContactsPage deleteContact() {
        isPageOpened();
        actionsButton.click();
        deleteButton.click();
        return new ContactsPage(driver, wait);
    }
}
