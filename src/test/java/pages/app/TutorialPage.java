package pages.app;

import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TutorialPage {

    private final AndroidDriver driver;

    // === XPATH CONSTANTS ===
    private static final String XPATH_ASSIGNED_ROUTE = "//android.widget.TextView[@text='Assigned Route']";
    private static final String XPATH_DIRECT_BOOKING = "//android.widget.TextView[@text='Direct Booking']";
    private static final String XPATH_TICKET_BOOKING = "//android.widget.TextView[@text='Ticket Booking']";
    private static final String XPATH_TUTORIAL_DYNAMIC = "//android.widget.TextView[@text='%s']";
    private static final String XPATH_TUTORIAL_STARTED = "//android.widget.TextView[contains(@text, 'Tutorial')]";
    private static final String XPATH_START_TUTORIAL = "//android.widget.Button[contains(@text, 'Start Tutorial')]";
    private static final String XPATH_QUIT_TUTORIAL = "//android.widget.TextView[contains(@text, 'Quit')]";

    public TutorialPage(AndroidDriver driver) {
        this.driver = driver;
    }

    public void verifyTutorialSections() {

    }

    public void selectTutorial(String tutorialName) {
    }

    public void verifyTutorialStarted() {

    }



    public void quitActiveTutorialIfExists() {
        try {

        } catch (Exception e) {

        }
    }
}
