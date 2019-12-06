import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {
        AppiumDriver driver;
        public static final String URL = "http://127.0.0.1:4723/wd/hub";

        @Before
        public void androidLaunchApp() {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "emulator-5554");
                capabilities.setCapability("app", "/Users/mmendoza/workspaceMinerva/SmartLine-Android/app/build/outputs/apk/alpha/debug/app-alpha-debug.apk");
                capabilities.setCapability("appPackage", "com.godaddy.smartline.alpha");
                capabilities.setCapability("appActivity", "com.godaddy.smartline.ui.splash.SplashActivity");

                driver = new AppiumDriver(new URL(URL), capabilities);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            } catch (Exception exp) {
                System.out.println("Cause is:  " + exp.getCause());
                System.out.println("Message is:  " + exp.getMessage());
                exp.printStackTrace();
            }
        }

    public void verifyHomeScreenUI() {
        MobileElement accountButton = (MobileElement) driver.findElementByAccessibilityId("Navigate up");
        MobileElement homeBottomNav = (MobileElement) driver.findElementByAccessibilityId("SML Alpha");
        MobileElement dialerBottomNav = (MobileElement) driver.findElementByAccessibilityId("Dialer");
        MobileElement contactsBottomNav = (MobileElement) driver.findElementByAccessibilityId("Contacts");


        accountButton.isDisplayed();
        homeBottomNav.isDisplayed();
        dialerBottomNav.isDisplayed();
        contactsBottomNav.isDisplayed();
    }

    public void signIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        MobileElement usernameField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement nextButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        usernameField.sendKeys("onelocal");
        nextButton.click();

        MobileElement passwordField = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/text_input_edit_text");
        MobileElement signInButton = (MobileElement) driver.findElementById("com.godaddy.smartline.alpha:id/sign_in_button");
        passwordField.sendKeys("2525Smart");
        signInButton.click();
        verifyHomeScreenUI();
    }


    @After
    public void teardown() {
            driver.quit();
    }
}