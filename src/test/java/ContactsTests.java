import io.appium.java_client.MobileElement;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class ContactsTests extends BaseClass {

    public void goToContactsFromSignIn() {
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/username");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/password");
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");

        usernameField.sendKeys("onelocal");
        passwordField.sendKeys("2525Smart");
        signInButton.click();
        List<MobileElement> contactsBottomNav = driver.findElementsByAccessibilityId("Contacts");
        contactsBottomNav.get(0).click();
    }

    @Test
    public void contactsAllowPermissions() {
        goToContactsFromSignIn();
        MobileElement permissionMessageAlert = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_message");
        MobileElement allowButton = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_allow_button");
        MobileElement denyButton = (MobileElement) driver.findElementById("com.android.packageinstaller:id/permission_deny_button");

        permissionMessageAlert.isDisplayed();
        allowButton.isDisplayed();
        denyButton.isDisplayed();
        allowButton.click();

        MobileElement contactList = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/recycler_view");
        assertTrue(contactList.isDisplayed());
    }
}