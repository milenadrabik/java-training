package pl.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageUsersHelper extends HelperBase {

    public ManageUsersHelper(ApplicationManager app) {
        super(app);
    }

    public void initModify() {
        click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=8']"));
    }

    public void resetPassword() {
        click(By.cssSelector("input[value='Reset Password']"));
    }

    public void setNewPassword(String confirmationLink, String newPassword) {
        wd.get(confirmationLink);
        type(By.name("password"), newPassword);
        type(By.name("password_confirm"), newPassword);
        click(By.cssSelector("input[value='Update User']"));
    }
}
