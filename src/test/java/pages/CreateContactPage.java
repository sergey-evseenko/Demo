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

public class CreateContactPage extends BasePage {

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;
    @FindBy(name = "email")
    WebElement inputEmail;
    @FindBy(name = "firstName")
    WebElement inputFirstName;
    @FindBy(name = "lastName")
    WebElement inputLastName;
    @FindBy(css = ".select__control")
    WebElement dropdownCompany;

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

    public void sleep() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Providing contact email, First name, Last name, company. Clicking submit.")
    public ContactDetailsPage provideContactDataAndSubmit(Contact contact) {
        String locator = "//div[contains(text(),'%s')]";
        isPageOpened();
        inputEmail.sendKeys(contact.getEmail());
        sleep();
        dropdownCompany.click();
        driver.findElement(By.xpath(String.format(locator, contact.getCompany()))).click();
        sleep();
        inputFirstName.sendKeys(contact.getFirstName());
        sleep();
        inputLastName.sendKeys(contact.getLastName());
        sleep();
        submitButton.click();
        return new ContactDetailsPage(driver, wait);

    }
}
