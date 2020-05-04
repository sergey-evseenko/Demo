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

    @FindBy(css = ".btn-primary")
    WebElement createCompanyButton;

    @FindBy(css = "input[type='search']")
    WebElement searchInput;


    public CompaniesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public BasePage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(createCompanyButton));
        return null;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Clicking Create company button")
    public CreateCompanyPage createCompanyButtonClick() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        isPageOpened();
        createCompanyButton.click();
        return new CreateCompanyPage(driver, wait);

    }

    @Step("Searching and opening company")
    public CompanyDetailsPage searchAndOpenCompany(Company company) {
        String locator = "//div[contains(text(),%s)]";
        isPageOpened();
        searchInput.sendKeys(company.getCompanyName());
        driver.findElement(By.xpath(String.format(locator, company.getCompanyName()))).click();
        return new CompanyDetailsPage(driver, wait);
    }
}
