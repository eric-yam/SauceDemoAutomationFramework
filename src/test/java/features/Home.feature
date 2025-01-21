@Home
Feature: Home Page Tests

  Scenario: Successfully Landed On Home Page
    Given User Logs In:
      | Username      | Password     |
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    And Home Page is Displayed

  Scenario: Validate Home Page Filter
    Given User Logs In:
      | Username      | Password     |
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    And Home Page is Displayed
    When User Applies Filter On Home Page And Validates Filter Applied
      | Name (A to Z)       |
      | Name (Z to A)       |
      | Price (low to high) |
      | Price (high to low) |

  Scenario: Add Products To Cart From Home Page
    Given User Logs In:
      | Username      | Password     |
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    And Home Page is Displayed

    Then Add Products To Cart Via Shortcut On Home Page
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |