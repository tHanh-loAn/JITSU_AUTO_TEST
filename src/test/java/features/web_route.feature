@webTest
Feature: Search city on OpenWeather

  Scenario Outline: Search city and verify result
    Given Opens OpenWeather website
    When Searches for city <searchText>
    Then Verify city name <cityName> is displayed
    And Verify the current date is displayed correctly
    And Verify the temperature is displayed correctly

    Examples:
      | searchText      | cityName    |
      | Los Angeles, US | Los Angeles |