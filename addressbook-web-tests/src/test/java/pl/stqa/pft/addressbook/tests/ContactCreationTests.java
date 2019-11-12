package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    public ContactCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactCreation() throws Exception {
        Contacts before = app.contact().all();
        app.goTo().newContactPage();
        File photo = new File("src/test/resources/cat.jpg");
        ContactData contact = new ContactData().withFirstName("Harry").withLastName("Hole").withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));
    }

}