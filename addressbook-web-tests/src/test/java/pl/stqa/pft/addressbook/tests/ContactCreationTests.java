package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactCreation() throws Exception {
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToAddNewContactPage();
        ContactData contact = new ContactData("Harry", "Hole", null, null, null);
        app.getContactHelper().createContact(contact,true);
        List<ContactData> after = app.getContactHelper().getContactList();
        assertEquals(after.size(), before.size() + 1);

        contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(contact);
        assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }

}