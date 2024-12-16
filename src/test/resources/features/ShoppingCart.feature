@ShoppingCart
Feature: Shopping Cart Page Tests

  Scenario: Successfully Add And Remove Products In Shopping Cart
    Given User Logs In:
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    And Home Page is Displayed

    And Add Products To Shopping Cart Via Product Page
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    And Open Shopping Cart Page
    Then Shopping Cart Page Is Displayed

    And Validate Number of Items In Cart
    And Remove Products In Shopping Cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    Then Validate Number of Items In Cart