package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactCreation() throws Exception {
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToAddNewContactPage();
        app.getContactHelper().createContact(new ContactData("Harry", "Hole", "111222333", "test65748@test.com", "[none]"), true);
        int after = app.getContactHelper().getContactCount();
        assertEquals(after, before + 1);
    }

}