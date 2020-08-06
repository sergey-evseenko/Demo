package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

@Log4j2
public class CreateContactPage extends BasePage {

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;
    @FindBy(name = "email")
    WebElement inputEmail;
    @FindBy(name = "firstName")
    WebElement inputFirstName;
    @FindBy(name = "lastName")
    WebElement inputLastName;
    //@FindBy(xpath = "//div[contains(text(), 'Select...')]")
    @FindBy(id = "react-select-3-input")
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
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Step("Providing contact email, First name, Last name, company. Clicking submit.")
    public ContactDetailsPage provideContactDataAndSubmit(Contact contact) {
        String locator = "//div[contains(text(),'%s')]";
        /*
        String param = "arguments[0].value='%s';";
        */
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Create contact' pop-up was success opened.");
        inputEmail.sendKeys(contact.getEmail());
        inputFirstName.sendKeys(contact.getFirstName());
        inputLastName.sendKeys(contact.getLastName());
        dropdownCompany.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(String.format(locator, contact.getCompany())))));
        driver.findElement(By.xpath(String.format(locator, contact.getCompany()))).click();
        /*
        ((JavascriptExecutor) driver).executeScript(String.format(param, contact.getFirstName()), inputFirstName);
        ((JavascriptExecutor) driver).executeScript(String.format(param, contact.getLastName()), inputLastName);
         */
        AllureUtils.takeScreenshot(driver);
        submitButton.click();
        return new ContactDetailsPage(driver, wait);

    }
}
