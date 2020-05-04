package test;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CompaniesPage;
import pages.CompanyDetailsPage;
import pages.CreateCompanyPage;
import pages.LoginPage;
import utils.CapabilitiesGenerator;

public abstract class BaseTest {
    LoginPage loginPage;
    CompaniesPage companiesPage;
    CreateCompanyPage createCompanyPage;
    CompanyDetailsPage companyDetailsPage;

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
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
        driver.quit();
    }


}
