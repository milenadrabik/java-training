package pl.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.stqa.pft.mantis.model.MailMessage;
import pl.stqa.pft.mantis.model.UserData;
import pl.stqa.pft.mantis.model.Users;
import ru.lanwen.verbalregex.VerbalExpression;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    public ResetPasswordTests(String browser) {
        super(browser);
    }

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String adminUser = "administrator";
        String adminPassword = "root";
        String login = "harry";
        String email = "hary3626238@localhost.localdomain";
        String newPassword = String.format("harry_new_password%s", now);

        app.login().performLogin(adminUser, adminPassword);
        app.goTo().manageUsersPage();
        app.manageUsers().initModify();
        app.manageUsers().resetPassword();

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.manageUsers().setNewPassword(confirmationLink, newPassword);
        assertTrue(app.newSession().login(login, newPassword));

    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
