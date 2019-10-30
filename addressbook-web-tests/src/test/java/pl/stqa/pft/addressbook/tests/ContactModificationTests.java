package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    public ContactModificationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Harry", "Hole", "111222333", "test65748@test.com", "[none]"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContactModification(before.size() - 1);
        ContactData contact = new ContactData("Harry", "Hole", null, null, null);
        app.getContactHelper().fillContactForm(contact,false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
