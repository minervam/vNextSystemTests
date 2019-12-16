import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class androidSignInTests extends androidBaseClass {

    public void verifySignInScreenUI() {
        MobileElement forgotButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/forgot");
        MobileElement enterYourGDUsernameText = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/description");
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement nextButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");

        forgotButton.isDisplayed();
        enterYourGDUsernameText.isDisplayed();
        nextButton.isDisplayed();
        usernameField.isDisplayed();
    }

    @Test
    public void validSignIn() {
        //enter valid username
        verifySignInScreenUI();
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement nextButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");

        usernameField.sendKeys("onelocal");
        nextButton.click();

        //verify password screen displays
        MobileElement backToUsenameButton = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        MobileElement enterYourPasswordText= (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/description");
        MobileElement forgotButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/forgot");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement finePrintText = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/fine_print");
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");

        assertTrue(forgotButton.isDisplayed());
        assertTrue(backToUsenameButton.isDisplayed());
        assertTrue(enterYourPasswordText.isDisplayed());
        assertTrue(passwordField.isDisplayed());
        assertTrue(signInButton.isDisplayed());
        assertTrue(finePrintText.isDisplayed());

        //enter valid password
        passwordField.sendKeys("2525Smart");
        MobileElement showHideButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/input_action_text");

        /*verify show hide button
        code for show hide btn goes here*/

        signInButton.click();
        verifyHomeScreenUI();
    }

    @Test
    public void invalidSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, 3);

        //Check if next button is disabled
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement nextButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        assertFalse(nextButton.isEnabled());

        //type 2 characters in username
        usernameField.sendKeys("in");
        assertFalse(nextButton.isEnabled());

        //type in an invalid username
        usernameField.sendKeys("invalidUsername");
        nextButton.click();
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        assertFalse(signInButton.isEnabled());

        //enter a valid password but username is invalid
        passwordField.sendKeys("2525Smart");
        signInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/error_text"))));

        //Go back and enter a valid username
        MobileElement backToUsername = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        backToUsername.click();
        usernameField.sendKeys("onelocal");
        nextButton.click();

        //enter an invalid password
        passwordField.sendKeys("invalidPassword");
        signInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/error_text"))));
    }
}