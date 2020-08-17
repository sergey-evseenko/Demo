package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Company;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CompanyDetailsPage extends BasePage {

    @FindBy(name = "companyName")
    WebElement inputCompanyName;

    @FindBy(name = "domainName")
    WebElement inputDomainName;

    @FindBy(xpath = "//span[contains(text(), 'Save')]")
    WebElement saveButton;

    @FindBy(xpath = "//p[text()='Company Created']")
    WebElement companyCreated;

    @FindBy(xpath = "//div[contains(text(), 'Delete')]")
    WebElement deleteButton;

    @FindBy(xpath = "//span[contains(text(),'•••')]")
    WebElement actionsMenu;

    public CompanyDetailsPage(WebDriver driver) {
        super(driver);
    }

    public CompanyDetailsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(companyCreated));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
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
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Company details' page was success opened.");
        inputCompanyName.clear();
        inputCompanyName.sendKeys(updatedCompany.getCompanyName());
        sleep();
        inputDomainName.clear();
        inputDomainName.sendKeys(updatedCompany.getCompanyDomain());
        sleep();
        saveButton.click();
        return this;
    }

    @Step("Verifying that company was updated")
    public void verifyUpdatedCompany(Company updatedCompany) {
        sleep();
        driver.navigate().refresh();
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("Company were updated with company name:" + updatedCompany.getCompanyName() + " and company domain: " + updatedCompany.getCompanyDomain());
        String actualCompanyName = inputCompanyName.getAttribute("value");
        assertEquals(actualCompanyName, updatedCompany.getCompanyName(), "Company name updating Error.");
        String actualDomainName = inputDomainName.getAttribute("value");
        assertEquals(actualDomainName, updatedCompany.getCompanyDomain(), "Company domain updating Error.");
    }

    @Step("Deleting company")
    public CompaniesPage deleteCompany() {
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("Company was opened.");
        actionsMenu.click();
        deleteButton.click();
        log.info("Delete button was clicked.");
        return new CompaniesPage(driver);
    }


}
