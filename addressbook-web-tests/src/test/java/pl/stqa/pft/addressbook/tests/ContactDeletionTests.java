package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {

    public ContactDeletionTests(String browser) {
        super(browser);
    }

    @Test
    public void testContactDeletion() {
        if(!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().goToAddNewContactPage();
            app.getContactHelper().createContact(new ContactData("Harry", "Hole", "111222333", "test65748@test.com", "[none]"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactDeletion();
        app.getContactHelper().confirmContactDeletion();
    }
}
