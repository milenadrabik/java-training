package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
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
        GroupData group = new GroupData("test2", null, null);
        app.getGroupHelper().createGroup(group);
        List<GroupData> after = app.getGroupHelper().getGroupList();
        assertEquals(after.size(), before.size() + 1);

        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        assertEquals(before, after);
    }

}