package pages;

import io.qameta.allure.Step;
import models.Company;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateCompanyPage extends BasePage {

    //@FindBy(css = "input[name='domainName']")
    @FindBy(name = "domainName")
    WebElement inputDomainName;

    //@FindBy(css = "input[name='companyName']")
    @FindBy(name = "companyName")
    WebElement inputCompanyName;

    @FindBy(css = "button[type='submit']")
    WebElement submitButton;


    public CreateCompanyPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }


    public CreateCompanyPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(submitButton));
        return this;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Providing company domain name and company name. Clicking submit.")
    public CompanyDetailsPage provideCompanyDataAndSubmit(Company company) {
        isPageOpened();
        inputDomainName.sendKeys(company.getCompanyDomain());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        inputCompanyName.sendKeys(company.getCompanyName());
        submitButton.click();
        return new CompanyDetailsPage(driver, wait);
    }
}
