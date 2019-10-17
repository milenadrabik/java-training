package pl.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import pl.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase{

    @Test
    public void testGroupModification() {
        app.goToGroupPage();
        app.selectGroup();
        app.initGroupModification();
        app.fillGroupForm(new GroupData("test1", "test2", "test3"));
        app.submitGroupModification();
        app.returnToGroupPage();
    }

}