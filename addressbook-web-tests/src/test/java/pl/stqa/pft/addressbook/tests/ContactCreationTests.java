package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests(String browser) {
        super(browser);
    }

    @Test(enabled = false)
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToAddNewContactPage();
        ContactData contact = new ContactData("Harry", "Hole", null, null, null);
        app.getContactHelper().createContact(contact, true);
        List<ContactData> after = app.getContactHelper().getContactList();
        assertEquals(after.size(), before.size() + 1);

        before.add(contact);
        Comparator<? super ContactData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }

}