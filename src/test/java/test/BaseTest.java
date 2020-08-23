package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.*;
import utils.CapabilitiesGenerator;
import utils.TestListener;

import java.util.concurrent.TimeUnit;

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

    //TODO Change BeforeMethod => BeforeClass
    @BeforeMethod(description = "Opening chrome Driver.")
    public void setDriver() {
        driver = new ChromeDriver(CapabilitiesGenerator.getChromeOptions());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        companyDetailsPage = new CompanyDetailsPage(driver);
        companiesPage = new CompaniesPage(driver);
        createCompanyPage = new CreateCompanyPage(driver);
        contactDetailsPage = new ContactDetailsPage(driver);
        contactsPage = new ContactsPage(driver);
        createContactPage = new CreateContactPage(driver);

    }

    @AfterMethod(description = "Closing chrome Driver.", alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }

}
