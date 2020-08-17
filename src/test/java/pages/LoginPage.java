package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.AllureUtils;


@Log4j2
public class LoginPage extends BasePage {


    @FindBy(css = "#username")
    WebElement inputEmail;
    @FindBy(css = "#password")
    WebElement inputPassword;
    @FindBy(css = "#kc-login")
    WebElement loginButton;
    @FindBy(xpath = "//div[contains(text(), 'Deals Revenue')]")
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
        driver.get("https://sell.qa.inperium.dev");
        isPageOpened();
        AllureUtils.takeScreenshot(driver);
        log.info("Login page was success opened.");
        return this;
    }

    @Step("Providing login and pass. Clicking login button.")
    public void login(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        loginButton.click();
        wait.until(ExpectedConditions.visibilityOf(widgetDealsRevenue));
        AllureUtils.takeScreenshot(driver);
        log.info("User was successfully logged in.");
    }
}
