import io.appium.java_client.AppiumDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseClass {
        AppiumDriver driver;
        public static final String URL = "http://127.0.0.1:4723/wd/hub";

        @Before
        public void setUp() {
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("deviceName", "emulator-5554");
                capabilities.setCapability("app", "/Users/mmendoza/workspaceMinerva/SmartLine-Android/app/build/outputs/apk/alpha/debug/app-alpha-debug.apk");
                capabilities.setCapability("appPackage", "com.godaddy.smartline.alpha");
                capabilities.setCapability("appActivity", "com.godaddy.smartline.ui.splash.SplashActivity");

                driver = new AppiumDriver(new URL(URL), capabilities);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            } catch(Exception exp) {
                System.out.println("Cause is:  " + exp.getCause());
                System.out.println("Message is:  " + exp.getMessage());
                exp.printStackTrace();
            }
        }

    @After
    public void teardown() {
            driver.quit();
    }
}