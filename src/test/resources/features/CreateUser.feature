Feature: Create user in OrangeHRM

  Scenario: Admin creates a new user successfully
    Given I open OrangeHRM login page
    When I login as admin user
    And I navigate to User Management page
    Then Admin header is displayed successfully
    And I create a new valid user
    Then The user should be created successfully
    When I search for the created user
    Then The user record found is displayed successfully
    Then The user should appear in result table
