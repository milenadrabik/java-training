package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupCreationTests extends TestBase {

    public GroupCreationTests(String browser) {
        super(browser);
    }

    @Test
    public void testGroupCreation() throws Exception {
        app.getNavigationHelper().goToGroupPage();
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));
        List<GroupData> after = app.getGroupHelper().getGroupList();
        assertEquals(after.size(), before.size() + 1);
    }

}