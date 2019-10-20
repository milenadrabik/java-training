package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().goToAddNewContactPage();
    app.getContactHelper().fillContactForm(new ContactData("Harry", "Hole", "111222333", "test65748@test.com"));
    app.getContactHelper().submitContactCreation();
    app.getContactHelper().returnToHomePage();
    app.getSessionHelper().logout();
  }

}