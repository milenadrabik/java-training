package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    public ContactModificationTests(String browser) {
        super(browser);
    }

    @Test
    public void testContractModification() {
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Harry", "Hole", "555666777", "test65748@test.com", null), false);
        app.getContactHelper().submitContactModification();
    }
}
