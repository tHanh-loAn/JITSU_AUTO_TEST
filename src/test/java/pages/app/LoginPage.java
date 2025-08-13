package pages.app;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;

public class LoginPage {

    private final AndroidDriver driver;

    // === ELEMENT ID CONSTANTS ===
    private static final String ID_USERNAME = "com.jitsu.driver:id/etUserName";
    private static final String ID_PASSWORD = "com.jitsu.driver:id/etPassword";
    private static final String ID_LOGIN_BUTTON = "com.jitsu.driver:id/btnLogin";

    public LoginPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {

    }

    public void enterPassword(String password) {

    }

    public void tapLogin() {

    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        tapLogin();
    }
}
