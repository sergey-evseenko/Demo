package pages;

import io.qameta.allure.Step;
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

    @FindBy(xpath = "//button[contains(text(), 'Save')]")
    WebElement saveButton;

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
    public CompanyDetailsPage updateAndSaveCompany(String updatedName) {
        isPageOpened();
        inputCompanyName.clear();
        inputCompanyName.sendKeys(updatedName);
        saveButton.click();
        return this;
    }

    @Step("Verifying that company was updated")
    public void verifyUpdatedCompany(String expectedName) {
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(inputCompanyName));
        String actualName = inputCompanyName.getAttribute("value");
        assertEquals(actualName, expectedName, "Company updating Error.");
    }


}
