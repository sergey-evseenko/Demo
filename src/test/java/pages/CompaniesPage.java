package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.Company;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;

import java.util.List;

import static org.testng.Assert.assertEquals;

@Log4j2
public class CompaniesPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    WebElement createCompanyButton;

    @FindBy(name = "query")
    WebElement searchInput;


    public CompaniesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public CompaniesPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(createCompanyButton));
        return this;
    }

    @Override
    @Step("Opening 'Companies' page")
    public CompaniesPage openPage() {
        driver.get("https://sell.qa.inperium.dev/companies");
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Companies' page was success opened.");
        return this;
    }

    @Step("Clicking 'Create company' button")
    public CreateCompanyPage createCompanyButtonClick() {
        createCompanyButton.click();
        log.info("'Create company' button was clicked.");
        return new CreateCompanyPage(driver, wait);

    }

    @Step("Searching and opening company")
    public CompanyDetailsPage searchAndOpenCompany(Company company) {
        String locator = "//span[contains(text(),'%s')]";
        searchInput.sendKeys(company.getCompanyName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, company.getCompanyName()))));
        AllureUtils.takeScreenshot(driver);
        log.info("Company with name: " + company.getCompanyName() + " was found.");
        driver.findElement(By.xpath(String.format(locator, company.getCompanyName()))).click();
        return new CompanyDetailsPage(driver, wait);
    }

    @Step("Verifying that company was successfully deleted")
    public CompaniesPage verifyThatCompanyWasDeleted(Company company) {
        String locator = "//span[contains(text(),'%s')]";
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Companies' page was opened.");
        searchInput.sendKeys(company.getCompanyName());
        sleep();
        sleep();
        List<WebElement> deletedCompany = driver.findElements(By.xpath(String.format(locator, company.getCompanyName())));
        assertEquals(deletedCompany.size(), 0, "Company deleting updating Error.");
        AllureUtils.takeScreenshot(driver);
        log.info("Company with name: " + company.getCompanyName() + " was success deleted.");
        return this;
    }
}
