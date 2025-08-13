package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.web.HomePage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static pages.web.HomePage.getCurrentLATime;

public class OpenWeatherSteps {
    private final HomePage homePage = new HomePage();

    @Given("Opens OpenWeather website")
    public void toOpenWeatherPage() {
        homePage.openHomePage();
    }

    @When("Searches for city {}")
    public void searchCity(String text) {
        homePage.searchCity(text);
    }

    @Then("Verify city name {} is displayed")
    public void verifyCityName(String expectedCity) {
        String actualCity = homePage.getDisplayedCityName();
        Assert.assertTrue("Expected city to contain: " + expectedCity + ", but got: " + actualCity,
                actualCity.contains(expectedCity));

    }

    @And("Verify the current date is displayed correctly")
    public void verifyCurrentDate() {
        String actualDate = homePage.getDisplayedDate();
        String today = getCurrentLATime();
        Assert.assertTrue("Expected date to contain: " + today + ", but got: " + actualDate,
                actualDate.contains(today));

    }

    @And("Verify the temperature is displayed correctly")
    public void verifyTemperature() {
        String temperature = homePage.getDisplayedTemperature();
        // Example: "27°C" -> remove °C and parse
        String numericPart = temperature.replaceAll("[^\\d.-]", "");
        try {
            Double.parseDouble(numericPart);
        } catch (NumberFormatException e) {
            Assert.fail("Temperature is not a valid number: " + temperature);
        }

    }
}
