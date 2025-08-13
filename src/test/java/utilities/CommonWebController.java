package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonWebController {
    private static WebDriver driver;
    private static final long TIMEOUT = 30L;

    public static WebDriver initDriverTest(String url) {
        WebDriverManager.chromedriver().setup();
        // Cấu hình ChromeOptions
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("headless"); // Bỏ comment nếu muốn chạy headless
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");
        driver = new ChromeDriver(options);
        // Mở fullscreen

        driver.manage().window().maximize();
        driver.get(url);
        // driver.manage().window().setSize(new Dimension(1440, 900));
        return driver;
    }

    public static WebElement waitForVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void click(WebElement element) {
        waitForVisible(element).click();
    }

    public static void sendKeys(WebElement element, String text) {
        waitForVisible(element).sendKeys(text);
    }

    public static String getText(WebElement element) {
        return waitForVisible(element).getText();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
