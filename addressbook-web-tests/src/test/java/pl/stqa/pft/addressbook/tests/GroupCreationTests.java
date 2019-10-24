package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase{

  public GroupCreationTests(String browser) {
    super(browser);
  }

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
    app.getSessionHelper().logout();
  }

}