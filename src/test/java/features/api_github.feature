@api_github
Feature: GitHub Repository Information

  This feature verifies repository information using GitHub API

  Background:
    Given The GitHub organization is "SeleniumHQ"

  Scenario: Count total open issues across all repositories
    When Retrieve all repositories of the organization
    Then The total number of open issues should be shown
    And The repositories should be sorted by updated date in descending order
    And The repository with the most watchers should be shown
