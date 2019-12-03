import io.appium.java_client.MobileElement;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.*;

public class SignInTests extends BaseClass {

    public void verifySignInScreenUI() {
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/username");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/password");

        signInButton.isDisplayed();
        usernameField.isDisplayed();
        passwordField.isDisplayed();
    }

    @Test
    public void validSignIn() {
        verifySignInScreenUI();
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/username");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/password");
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");

        usernameField.sendKeys("onelocal");
        passwordField.sendKeys("2525Smart");
        signInButton.click();

        MobileElement accountButton = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        assertTrue(accountButton.isDisplayed());
    }

    @Test
    public void invalidSignIn() {
        WebDriverWait wait = new WebDriverWait(driver, 3);

        //Sign in without username and password
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        signInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/error_message"))));

        //Sign in with invalid username
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/username");
        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/password");

        usernameField.sendKeys("invalidUsername");
        passwordField.sendKeys("2525Smart");
        signInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/error_message"))));

        //Sign in with invalid password
        usernameField.sendKeys("onelocal");
        passwordField.sendKeys("invalidpwd");
        signInButton.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(String.valueOf("com.godaddy.smartline.alpha:id/error_message"))));
    }
}