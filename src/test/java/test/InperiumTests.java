package test;

import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import models.Company;
import models.Contact;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.UUID;

public class InperiumTests extends BaseTest {
    Company company = new Company(UUID.randomUUID().toString(), UUID.randomUUID().toString() + ".com");
    Company updatedCompany = new Company(UUID.randomUUID().toString(), UUID.randomUUID().toString() + ".com");
    Contact contact = new Contact(UUID.randomUUID().toString() + "@gmail.com", UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Erat Eget Corporation");
    Contact updatedContact = new Contact(UUID.randomUUID().toString() + "@gmail.com", UUID.randomUUID().toString(), UUID.randomUUID().toString(), "Erat Eget Corporation");

    @BeforeMethod
    public void login() {
        loginPage
                .openPage()
                .login(email, password);
    }

    @Test(description = "1. Create company.", priority = 1)
    @Link("https://sell.qa.inperium.dev")
    @Issue("INS-1845")
    public void createCompany() {
        companiesPage
                .openPage()
                .createCompanyButtonClick()
                .provideCompanyDataAndSubmit(company)
                .verifyThatCompanyWasCreated(company);
    }

    @Test(description = "2. Update company.", priority = 2)
    public void updateCompany() {
        companiesPage
                .openPage()
                .searchAndOpenCompany(company)
                .updateAndSaveCompany(updatedCompany)
                .verifyUpdatedCompany(updatedCompany);
    }

    @Test(description = "3. Delete company.", priority = 3)
    public void deleteCompany() {
        companiesPage
                .openPage()
                .searchAndOpenCompany(updatedCompany)
                .deleteCompany()
                .verifyThatCompanyWasDeleted(updatedCompany);
    }

    @Test(description = "4. Create contact.", priority = 4)
    public void createContact() {
        contactsPage
                .openPage()
                .createContactButtonClick()
                .provideContactDataAndSubmit(contact)
                .verifyThatContactWasCreated(contact);
    }

    @Test(description = "5. Update contact.", priority = 5)
    public void updateContact() {
        contactsPage
                .openPage()
                .searchAndOpenContact(contact)
                .updateAndSaveContact(updatedContact)
                .verifyUpdatedContact(updatedContact);
    }

    @Test(description = "6. Delete contact.", priority = 6)
    public void deleteContact() {
        contactsPage
                .openPage()
                .searchAndOpenContact(updatedContact)
                .deleteContact()
                .verifyThatContactWasDeleted(updatedContact);
    }
}
