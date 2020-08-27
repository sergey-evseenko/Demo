package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;


@Log4j2
public class LoginPage extends BasePage {

    @FindBy(name = "email")
    WebElement inputEmail;
    @FindBy(name = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//span[contains(text(), 'Log In')]")
    WebElement loginButton;
    @FindBy(xpath = "//h4[contains(text(), 'John Doe')]")
    WebElement widgetDealsRevenue;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    @Step("Opening login page.")
    public LoginPage openPage() {
        driver.get("https://account.qa.inperium.dev/login");
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("Login page was success opened.");
        return this;
    }

    @Step("Providing login and pass. Clicking login button.")
    public void login(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(widgetDealsRevenue));
        AllureUtils.takeScreenshot(driver);
        log.info("User was successfully logged in.");
    }
}
