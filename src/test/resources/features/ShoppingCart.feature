@ShoppingCart
Feature: Shopping Cart Page Tests

  Scenario: Successfully Add And Remove Products In Shopping Cart
    Given User Logs In:
      | Username      | Password     |
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

    And Validate Number of Items In Cart On Shopping Cart Page
    And Remove Products In Shopping Cart
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    Then Validate Number of Items In Cart On Shopping Cart Page

  Scenario: Verify Functionality of Shopping Cart Page
    Given User Logs In:
      | Username      | Password     |
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    Then Home Page is Displayed

    When Open Shopping Cart Page
    Then Shopping Cart Page Is Displayed

    When User Returns To Home Page From Shopping Cart Page
    And Open Shopping Cart Page
    And User Begins Checkout
    Then Checkout Information Page Is Displayed

  Scenario: Validate Total Cost of Items Being Purchased
    Given User Logs In:
      | Username      | Password     |
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
    And Shopping Cart Page Is Displayed
    Then Validate Sub-Total Cost Of Items Purchased On Shopping Cart Page