package pages;

import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {


    @FindBy(css = "#username")
    WebElement inputEmail;

    @FindBy(css = "#password")
    WebElement inputPassword;

    @FindBy(css = "#kc-login")
    WebElement loginButton;


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
        driver.get("https://sell.qa.inperium.dev");
        isPageOpened();
        return this;
    }

    @Step("Providing login and pass. Clicking login button.")
    public CompaniesPage login(User user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        loginButton.click();
        return new CompaniesPage(driver, wait);
    }
}
