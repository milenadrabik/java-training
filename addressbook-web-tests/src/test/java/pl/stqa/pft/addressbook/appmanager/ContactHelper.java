package pl.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pl.stqa.pft.addressbook.model.ContactData;
import pl.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.AssertJUnit.assertFalse;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByValue("[none]");
        } else {
            assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void returnToHomePage() {
        click(By.linkText("home page"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void initContactDeletion() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void confirmContactDeletion() {
        wd.switchTo().alert().accept();
    }

    public void create(ContactData contact, boolean creation) {
        fillContactForm(contact, creation);
        submitContactCreation();
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        initContactDeletion();
        confirmContactDeletion();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.cssSelector("img[title='Edit']"));
    }

    public int getContactCount() {
        List<WebElement> cells = wd.findElements(By.xpath("//tbody//tr[@name='entry']"));
        return cells.size();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tbody//tr[@name='entry']"));
        for (WebElement row : rows) {
            String firstname = row.findElements(By.tagName("td")).get(2).getText();
            String lastname = row.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
        }
        return contacts;
    }
}