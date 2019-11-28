package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;
import pl.stqa.pft.addressbook.model.Groups;

import java.util.Iterator;

import static org.testng.Assert.assertFalse;

public class RemoveContactFromGroupTests extends TestBase {

    ContactData contact;
    GroupData group;

    public RemoveContactFromGroupTests(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().newContactPage();
            app.contact().create(new ContactData()
                    .withFirstName("Harry").withLastName("Hole").withMobilePhone("111222333").withEmail("test65748@test.com"), true);
        }
        app.goTo().groupPage();

        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test_group" + (int) (Math.random() * 100)));
        }

        Iterator<GroupData> groupIterator = app.db().groups().iterator();
        contact = app.db().contacts().iterator().next();
        group = groupIterator.next();
        Groups contactGroups = contact.getGroups();

        while (!contactGroups.contains(group)) {
            if (groupIterator.hasNext()) {
                group = groupIterator.next();
            } else {
                app.goTo().homePage();
                app.contact().addContactToGroup(contact, group);
                break;
            }
        }
    }

    @Test
    public void testRemoveContactFromGroup() throws Exception {
        app.goTo().homePage();
        app.contact().removeContactFromGroup(group, contact);
        ContactData after = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId()).iterator().next();
        Groups contactGroups = after.getGroups();
        assertFalse(contactGroups.contains(group));
    }
}
