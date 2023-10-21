Feature: Add Product

  @Positive
  Scenario: add product to cart
    Given user login page
    When user input username
    And user input password
    And user click login
    Then user should see products homepage
    When user click add to cart
    Then product should added to cart