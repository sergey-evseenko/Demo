package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Company;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CompanyDetailsPage extends BasePage {

    @FindBy(css = ".NoteForm_note__form__JsmUJ")
    WebElement actionsMenu;

    @FindBy(name = "companyName")
    WebElement inputCompanyName;

    @FindBy(name = "domainName")
    WebElement inputDomainName;

    @FindBy(xpath = "//div[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//span[contains(text(), '•••')]")
    WebElement actionsButton;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    WebElement deleteButton;

    public CompanyDetailsPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public CompanyDetailsPage isPageOpened() {
        return this;
    }

    @Override
    public CompanyDetailsPage openPage() {
        wait.until(ExpectedConditions.visibilityOf(actionsButton));
        return this;
    }

    @Step("Verifying that company was correctly created.")
    public void verifyThatCompanyWasCreated(Company company) {
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Company details' page was success opened.");
        String actualCompanyName = inputCompanyName.getAttribute("value");
        assertEquals(actualCompanyName, company.getCompanyName(), "Company creating Error. Name.");
        String actualDomainName = inputDomainName.getAttribute("value");
        assertEquals(actualDomainName, company.getCompanyDomain(), "Company creating Error. Domain");
        log.info("Company was successfully created. Company name: " + company.getCompanyName());
    }

    @Step("Updating and saving company.")
    public CompanyDetailsPage updateAndSaveCompany(Company updatedCompany) {
        wait.until(ExpectedConditions.visibilityOf(inputCompanyName));
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
        wait.until(ExpectedConditions.visibilityOf(inputCompanyName));
        actionsButton.click();
        deleteButton.click();
        return new CompaniesPage(driver, wait);
    }


}
