package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AllureUtils;


@Log4j2
public class LoginPage extends BasePage {


    @FindBy(css = "#username")
    WebElement inputEmail;
    @FindBy(css = "#password")
    WebElement inputPassword;
    @FindBy(css = "#kc-login")
    WebElement loginButton;
    @FindBy(xpath = "//div[contains(text(), 'Create')]")
    WebElement createCompanyButton;


    public LoginPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
        PageFactory.initElements(driver, this);
    }

    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    @Step("Opening login page.")
    public LoginPage openPage() {
        driver.get("https://sell.inperium.dev");
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("Login page was success opened.");
        return this;
    }

    @Step("Providing login and pass. Clicking login button.")
    public CompaniesPage login(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        loginButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().refresh();
        wait.until(ExpectedConditions.visibilityOf(createCompanyButton));
        AllureUtils.takeScreenshot(driver);
        log.info("User was successfully logged in.");
        return new CompaniesPage(driver, wait);
    }
}
