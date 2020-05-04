package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CompaniesPage extends BasePage {

    @FindBy(css = ".btn-primary")
    WebElement createCompanyButton;

    public CompaniesPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    @Override
    public BasePage isPageOpened() {
        return null;
    }

    @Override
    public BasePage openPage() {
        return null;
    }

    @Step("Clicking Create company button")
    public CreateCompanyPage createCompanyButtonClick() {
        wait.until(ExpectedConditions.visibilityOf(createCompanyButton));
        createCompanyButton.click();
        return new CreateCompanyPage(driver, wait);

    }
}
