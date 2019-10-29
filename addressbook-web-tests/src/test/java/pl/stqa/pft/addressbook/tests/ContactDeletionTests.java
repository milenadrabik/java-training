package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class ContactDeletionTests extends TestBase {

    public ContactDeletionTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactDeletion() {
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Harry", "Hole", "111222333", "test65748@test.com", "[none]"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().confirmContactDeletion();
        app.getNavigationHelper().goToHomePage();
        List<ContactData> after = app.getContactHelper().getContactList();
        assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() - 1);
        assertEquals(before, after);
    }
}
