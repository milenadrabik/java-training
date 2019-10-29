package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    public GroupModificationTests(String browser) {
        super(browser);
    }

    @Test
    public void testGroupModification() {
        app.getNavigationHelper().goToGroupPage();
        if (!app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData("test1", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData("test4", "test2", null));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();
        assertEquals(after.size(), before.size());
    }

}