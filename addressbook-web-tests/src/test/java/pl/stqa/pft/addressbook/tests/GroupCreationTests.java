package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    public GroupCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        int before = app.getGroupHelper().getGroupCount();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        int after = app.getGroupHelper().getGroupCount();
        assertEquals(after, before + 1);
    }

}