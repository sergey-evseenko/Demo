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

@Log4j2
public class CreateCompanyPage extends BasePage {

    @FindBy(name = "domainName")
    WebElement inputDomainName;
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
        //String param = "arguments[0].value='%s';";
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("'Create companies' pop-up was success opened.");
        inputDomainName.sendKeys(company.getCompanyDomain());
        inputCompanyName.sendKeys(company.getCompanyName());
        //((JavascriptExecutor) driver).executeScript(String.format(param, company.getCompanyName()), inputCompanyName);
        AllureUtils.takeScreenshot(driver);
        submitButton.click();
        return new CompanyDetailsPage(driver, wait);
    }
}
