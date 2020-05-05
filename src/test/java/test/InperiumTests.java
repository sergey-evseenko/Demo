package test;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import models.Company;
import models.Contact;
import models.User;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.util.UUID;


public class InperiumTests extends BaseTest {
    Company company = new Company(UUID.randomUUID().toString(), UUID.randomUUID().toString() + ".com");
    User user = new User("administrator@inperium.com", "123");
    Contact contact = new Contact(UUID.randomUUID().toString() + "@gmail.com", UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Erat Eget Corporation");

    @Test(description = "Create company.", priority = 1)
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

    @Test(description = "Update company.", priority = 2)
    public void updateCompany() {
        String updatedName = UUID.randomUUID().toString();
        loginPage
                .openPage()
                .login(user)
                .searchAndOpenCompany(company)
                .updateAndSaveCompany(updatedName)
                .verifyUpdatedCompany(updatedName);
    }

    @Test(description = "Delete contact.", priority = 3)
    @Ignore
    public void deleteCompany() {

    }

    @Test(description = "Create contact.", priority = 4)
    public void createContact() {
        loginPage
                .openPage()
                .login(user);
        contactsPage
                .openPage()
                .createContactButtonClick()
                .provideContactDataAndSubmit(contact)
                .verifyThatContactWasCreated();
    }

    @Test(description = "Update contact.", priority = 5)
    @Ignore
    public void updateContact() {

    }

    @Test(description = "Delete contact.", priority = 6)
    @Ignore
    public void deleteContact() {

    }


}
