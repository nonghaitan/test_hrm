Feature: GitHub Repository Analysis

  Scenario: Count total open issues
    Given I fetch all repositories of "SeleniumHQ"
    When I calculate the total number of open issues
    Then The total number of open issues should be greater than or equal to 0

  Scenario: Find highest-star repository
    Given I fetch all repositories of "SeleniumHQ"
    When I determine the repository with the highest stars
    Then The repository name should not be empty
    And The number of stars should be greater than or equal to 0
