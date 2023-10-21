Feature: Login to Swag Labs website

  @Positive
  Scenario: success login - login using registered username and password
    Given user website login page
    When user input registered username
    And user input registered password
    And user click login button
    Then user should see products page

  @Negative
  Scenario: failed login - login using unregistered username and password
    Given user website login page
    When user input unregistered username
    And user input unregistered password
    And user click login button
    Then user should see error alerts