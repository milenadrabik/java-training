package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).equals("Groups")
                && isElementPresent(By.name("new"))) {
            return;
        } else {
            click(By.linkText("groups"));
        }
    }

    public void newContactPage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).equals("Edit / add address book entry")) {
            return;
        } else {
            click(By.linkText("add new"));
        }
    }

    public void homePage() {
        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).equals("Address Book")) {
            return;
        } else {
            click(By.linkText("home"));
        }
    }
}