package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.CapabilitiesGenerator;
import utils.TestListener;

@Listeners(TestListener.class)
public abstract class BaseTest {
    LoginPage loginPage;
    CompaniesPage companiesPage;
    ContactsPage contactsPage;
    CreateCompanyPage createCompanyPage;
    CreateContactPage createContactPage;
    CompanyDetailsPage companyDetailsPage;
    ContactDetailsPage contactDetailsPage;

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    @BeforeMethod(description = "Opening chrome Driver.")
    public void setDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        webDriverWait = new WebDriverWait(this.driver, 30);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver, webDriverWait);
        companyDetailsPage = new CompanyDetailsPage(driver, webDriverWait);
        companiesPage = new CompaniesPage(driver, webDriverWait);
        createCompanyPage = new CreateCompanyPage(driver, webDriverWait);
        contactDetailsPage = new ContactDetailsPage(driver, webDriverWait);
        contactsPage = new ContactsPage(driver, webDriverWait);
        createContactPage = new CreateContactPage(driver, webDriverWait);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }


}
