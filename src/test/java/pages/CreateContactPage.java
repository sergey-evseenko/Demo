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

import java.util.List;

public class CreateContactPage extends BasePage {

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;

    @FindBy(name = "email")
    WebElement inputEmail;

    @FindBy(name = "firstName")
    WebElement inputFirstName;

    @FindBy(name = "lastName")
    WebElement inputLastName;

    public CreateContactPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public CreateContactPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Providing contact email, First name, Last name, company. Clicking submit.")
    public ContactDetailsPage provideContactDataAndSubmit(Contact contact) {
        isPageOpened();
        inputEmail.sendKeys(contact.getEmail());
        List<WebElement> dropdowns = driver.findElements(By.cssSelector(".select__value-container"));
        dropdowns.get(0).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[contains(text(), 'Massa Consulting')]")).click();
        inputFirstName.sendKeys(contact.getFirstName());
        inputLastName.sendKeys(contact.getLastName());
        submitButton.click();
        return new ContactDetailsPage(driver, wait);

    }
}
