package pages;

import io.qameta.allure.Step;
import models.Company;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class CompanyDetailsPage extends BasePage {

    @FindBy(css = ".NoteForm_note__form__JsmUJ")
    WebElement actionsMenu;

    @FindBy(name = "companyName")
    WebElement inputCompanyName;

    @FindBy(name = "domainName")
    WebElement inputDomainName;

    @FindBy(xpath = "//button[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//button[contains(text(), 'Actions')]")
    WebElement actionsButton;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    WebElement deleteButton;

    public CompanyDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public CompanyDetailsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(actionsMenu));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Verifying that company details page was opened.")
    public void verifyThatCompanyWasCreated() {
        isPageOpened();
    }

    @Step("Updating and saving company.")
    public CompanyDetailsPage updateAndSaveCompany(Company updatedCompany) {
        isPageOpened();
        inputCompanyName.clear();
        inputCompanyName.sendKeys(updatedCompany.getCompanyName());
        inputDomainName.clear();
        inputDomainName.sendKeys(updatedCompany.getCompanyDomain());
        saveButton.click();
        return this;
    }

    @Step("Verifying that company was updated")
    public void verifyUpdatedCompany(Company updatedCompany) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(inputCompanyName));
        String actualCompanyName = inputCompanyName.getAttribute("value");
        assertEquals(actualCompanyName, updatedCompany.getCompanyName(), "Company name updating Error.");
        String actualDomainName = inputDomainName.getAttribute("value");
        assertEquals(actualDomainName, updatedCompany.getCompanyDomain(), "Company domain updating Error.");
    }

    @Step("Deleting company")
    public CompaniesPage deleteCompany() {
        isPageOpened();
        actionsButton.click();
        deleteButton.click();
        return new CompaniesPage(driver, wait);
    }


}
