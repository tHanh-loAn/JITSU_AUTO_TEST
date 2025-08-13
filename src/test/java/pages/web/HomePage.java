package pages.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.CommonWebController;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

public class HomePage {
    private final String url = "https://openweathermap.org/";
    private final By searchInput = By.xpath("//div[@class='search-container']/input");
    private final By searchButton = By.xpath("//button[@type='submit' and @class='button-round dark']");
    private final By resultSearch = By.xpath("//span[contains(text(),'Los Angeles, US')]");

    private final By cityNameLabel = By.xpath("//div[@class='current-container mobile-padding']//h2");
    private final By dateLabel = By.xpath("//div[@class='current-container mobile-padding']//h2/preceding-sibling::span");
    private final By temperatureLabel = By.xpath("//div[@class='current-container mobile-padding']//span[@class='heading']");

    public void openHomePage() {
        CommonWebController.initDriverTest(url);
    }

    public void searchCity(String city) {
        WebElement inputSearchBox = CommonWebController.getDriver().findElement(searchInput);
        CommonWebController.sendKeys(inputSearchBox, city);
        WebElement buttonSearch = CommonWebController.getDriver().findElement(searchButton);
        CommonWebController.click(buttonSearch);
        WebDriverWait wait = new WebDriverWait(CommonWebController.getDriver(), Duration.ofSeconds(15));
        List<WebElement> suggestions = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(resultSearch));
        WebElement target = suggestions.get(0);
        CommonWebController.click(target);

    }
    public static String getCurrentLATime() {
        ZonedDateTime laTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mma", Locale.ENGLISH);
        return laTime.format(formatter).replace("AM", "am").replace("PM", "pm");
    }

    public String getDisplayedCityName() {

        WebDriverWait wait = new WebDriverWait(CommonWebController.getDriver(), Duration.ofSeconds(10));
        String oldCity = CommonWebController.getDriver()
                .findElement(cityNameLabel)
                .getText();
         wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(cityNameLabel, oldCity)));
        String newCity = CommonWebController.getDriver()
                .findElement(cityNameLabel)
                .getText();
        System.out.println("City sau khi search: " + newCity);
        return newCity;
    }

    public String getDisplayedDate() {
        WebDriverWait wait = new WebDriverWait(CommonWebController.getDriver(), Duration.ofSeconds(10));
        String oldCity = CommonWebController.getDriver()
                .findElement(dateLabel)
                .getText();
        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(cityNameLabel, oldCity)));
        String Time = CommonWebController.getDriver()
                .findElement(dateLabel)
                .getText();
        System.out.println("Time: " + Time);
        return Time;

    }

    public String getDisplayedTemperature() {
        WebElement labelTemperature = CommonWebController.getDriver().findElement(temperatureLabel);
        return CommonWebController.getText(labelTemperature).trim();
    }
}
