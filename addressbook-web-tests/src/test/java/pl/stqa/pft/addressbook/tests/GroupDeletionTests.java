package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class GroupDeletionTests extends TestBase {

    public GroupDeletionTests(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("test1"));
        }
    }

    @Test
    public void testGroupDeletion() throws Exception {
        app.goTo().groupPage();
        Set<GroupData> before = app.group().all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Set<GroupData> after = app.group().all();
        assertEquals(after.size(), before.size() - 1);

        before.remove(deletedGroup);
        assertEquals(before, after);
    }

}