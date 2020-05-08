package pages;

import io.qameta.allure.Step;
import models.Company;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompaniesPage extends BasePage {

    @FindBy(xpath = "//div[contains(text(), 'Create')]")
    WebElement createCompanyButton;

    @FindBy(name = "query")
    WebElement searchInput;


    public CompaniesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public BasePage isPageOpened() {
        return null;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Clicking 'Create company' button")
    public CreateCompanyPage createCompanyButtonClick() {
        createCompanyButton.click();
        return new CreateCompanyPage(driver, wait);

    }

    @Step("Searching and opening company")
    public CompanyDetailsPage searchAndOpenCompany(Company company) {
        String locator = "//div[contains(text(),'%s')]";
        searchInput.sendKeys(company.getCompanyName());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(locator, company.getCompanyName()))));
        driver.findElement(By.xpath(String.format(locator, company.getCompanyName()))).click();
        return new CompanyDetailsPage(driver, wait);
    }

    @Step("Verifying that company was successfully deleted")
    public void verifyThatCompanyWasDeleted(Company updatedCompany) {
        //TBD
    }
}
