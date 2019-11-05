package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ContactModificationTests extends TestBase {

    public ContactModificationTests(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData()
                    .withFirstname("Harry").withLastname("Hole").withMobile("111222333").withEmail("test65748@test.com").withGroup("[none]"), true);
        }
    }

    @Test
    public void testContactModification() {
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Harry").withLastname("Hole");
        app.contact().modify(contact);
        Set<ContactData> after = app.contact().all();
        assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        assertEquals(before, after);
    }

}