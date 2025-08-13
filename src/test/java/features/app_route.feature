@app_route
Feature: Tutorial feature for drivers in Jitsu app

  Scenario Outline: Driver starts Assigned Route tutorial and quits if already exists
    Given The driver has opened the Jitsu app
    When The driver logs in with username <username> and password <password>
    And The driver navigates to the Tutorials screen
    Then The driver should see tutorial sections: Assigned Route, Direct Booking, Ticket Booking
    When The driver selects the "Assigned Route" tutorial
    Then The Assigned Route tutorial should start
    And If a tutorial is already active, the driver quits it
    Examples:
      | username    | password  |
      | auto_244332 | Testing1! |