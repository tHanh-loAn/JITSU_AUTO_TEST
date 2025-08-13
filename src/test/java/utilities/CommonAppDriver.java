package utilities;

import io.appium.java_client.android.AndroidDriver;

public class CommonAppDriver {
    private static AndroidDriver driver;

    public static AndroidDriver getDriver() throws Exception {
//        if (driver == null) {
//            DesiredCapabilities caps = new DesiredCapabilities();
//            caps.setCapability("platformName", "Android");
//            caps.setCapability("deviceName", "Android Emulator");
//            caps.setCapability("automationName", "UiAutomator2");
//            caps.setCapability("app", "/path/to/your.apk"); // cập nhật lại path này
//            caps.setCapability("autoGrantPermissions", true);
//
//            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
//            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        }
        return driver;
    }
}
