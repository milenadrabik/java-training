package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDetailsTests extends TestBase {

    public ContactDetailsTests(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contact().all().size() == 0) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Harry").withLastName("Hole").withHomePhone("666-777-888").withWorkPhone("333 444 555")
                    .withAddress("street 1\nKrakow").withEmail("example@co.co").withEmail3("exampe@fo.fo"), true);
        }
    }

    @Test
    public void testContactDetails() throws Exception {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);
        ContactData contactInfoDetails = app.contact().infoDetails(contact);
        assertThat(contactInfoDetails.getAllData(), equalTo(mergeAllData(contactInfoFromEditForm)));
    }

    private String mergeAllData(ContactData contact) {
        String name = Arrays.asList(
                contact.getFirstName(),
                contact.getLastName()
        ).stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining(" "));
        String address = contact.getAddress();
        String phones = Arrays.asList(
                "H: " + contact.getHomePhone(),
                "M: " + contact.getMobilePhone(),
                "W: " + contact.getWorkPhone()
        ).stream().filter((s) -> !s.equals("H: ") && !s.equals("M: ") && !s.equals("W: "))
                .collect(Collectors.joining("\n"));
        String emails = Arrays.asList(
                contact.getEmail(),
                contact.getEmail2(),
                contact.getEmail3()
        ).stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
        String contactData = Arrays.asList(
                address,
                phones,
                emails
        ).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n\n"));
        if (!name.equals("") && address.equals("") && (!phones.equals("") || !emails.equals(""))) {
            name += "\n";
        }
        return Arrays.asList(name, contactData).stream().filter((s) -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}
