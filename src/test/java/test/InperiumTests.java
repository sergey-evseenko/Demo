package test;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import models.Company;
import models.User;
import org.testng.annotations.Test;


public class InperiumTests extends BaseTest {
    Company company = new Company("TestCompanyName", "testDomain.com");
    User user = new User("administrator@inperium.com", "123");

    @Test(description = "Create new company.")
    @Link("https://sell.inperium.dev")
    @Issue("INS-1845")
    public void createCompany() {
        loginPage
                .openPage()
                .login(user)
                .createCompanyButtonClick()
                .provideCompanyDataAndSubmit(company)
                .verifyThatCompanyWasCreated();
    }

}
