package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.GroupData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver wd;

    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper = new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public void logout() {
        sessionHelper.logout();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public void selectGroup() {
        groupHelper.selectGroup();
    }

    public void deleteSelectedGroups() {
        groupHelper.deleteSelectedGroups();
    }

    public void returnToGroupPage() {
        groupHelper.returnToGroupPage();
    }

    public void initGroupCreation() {
        groupHelper.initGroupCreation();
    }

    public void fillGroupForm(GroupData groupData) {
        groupHelper.fillGroupForm(groupData);
    }

    public void submitGroupCreation() {
        groupHelper.submitGroupCreation();
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void goToGroupPage() {
        navigationHelper.goToGroupPage();
    }

    public void goToAddNewContactPage() {
        navigationHelper.goToAddNewContactPage();
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }

    public void submitContactCreation() {
        contactHelper.submitContactCreation();
    }

    public void fillContactForm(ContactData contactData) {
        contactHelper.fillContactForm(contactData);
    }

    public void returnToHomePage() {
        contactHelper.returnToHomePage();
    }

    public void stop() {
        wd.quit();
    }

    private boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}