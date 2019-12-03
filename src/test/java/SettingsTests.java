import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class SettingsTests extends BaseClass {

    public void swipeDown() {
        TouchAction swipeDown = new TouchAction(driver);
        swipeDown.press(PointOption.point(650, 1990)).waitAction(WaitOptions.waitOptions(ofSeconds(1)))
                .moveTo(PointOption.point(650, 1000)).release().perform();
    }

    public void verifyAccountScreenUI() {
        MobileElement backButton = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        MobileElement settingsGearButton = (MobileElement) driver.findElementByAccessibilityId("Profile");
        MobileElement shareButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/share_smartline_number");
        MobileElement smartLineNumber = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/smartline_number");
        MobileElement yourNumberText = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/your_number");
        MobileElement sendCallsToggle = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/value_switch");

        backButton.isDisplayed();
        settingsGearButton.isDisplayed();
        shareButton.isDisplayed();
        smartLineNumber.isDisplayed();
        yourNumberText.isDisplayed();
        sendCallsToggle.isDisplayed();

        swipeDown();
        MobileElement signOutButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_out_button");
        signOutButton.isDisplayed();
    }

    public void goToAccountFromSignIn() {
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/username");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/password");

        usernameField.sendKeys("onelocal");
        passwordField.sendKeys("2525Smart");
        signInButton.click();
        MobileElement accountButton = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        accountButton.click();
        verifyAccountScreenUI();
    }

    @Test
    public void toggleSendCallsThenSignOut() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        goToAccountFromSignIn();

        MobileElement sendCallsToggle = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/value_switch");
        sendCallsToggle.click(); //to turn on
        sendCallsToggle.click(); //to turn off

        MobileElement signOutButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_out_button");
        signOutButton.click();

        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/sign_in_button"))));
    }
}
