package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactCreation() throws Exception {
        Set<ContactData> before = app.contact().all();
        app.goTo().newContactPage();
        ContactData contact = new ContactData().withFirstname("Harry").withLastname("Hole");
        app.contact().create(contact, true);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size() + 1);

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        assertEquals(before, after);
    }

}