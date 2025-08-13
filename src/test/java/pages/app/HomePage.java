package pages.app;

        import io.appium.java_client.android.AndroidDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.By;

public class HomePage {
    private final AndroidDriver driver;

    // === XPATH CONSTANTS ===
    private static final String XPATH_PROFILE = "//android.widget.TextView[@text='Profile']";
    private static final String XPATH_TUTORIALS = "//android.widget.TextView[@text='Tutorials']";

    public HomePage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void goToTutorials() {

    }
}
