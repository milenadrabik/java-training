package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContractModification() {
        app.initContactModification();
        app.fillContactForm(new ContactData("Harry", "Hole", "555666777", "test65748@test.com"));
        app.submitContactModification();
    }
}
