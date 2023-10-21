Feature: Sort Product

  @Positive
  Scenario: sort product by name and price
    Given user start login page
    When user input account username
    And user input account password
    And login button clicked by user
    Then user should on products page
    When user click product sort icon
    And user click the name z to a option
    Then user should see the products sorted