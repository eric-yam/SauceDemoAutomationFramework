@Checkout
Feature: Checkout Tests

  Scenario: Checkout Products From Shopping Cart
    Given User Logs In:
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    Then Home Page is Displayed

    When Add Products To Shopping Cart Via Product Page
      | Sauce Labs Backpack      |
      | Sauce Labs Bike Light    |
      | Sauce Labs Bolt T-Shirt  |
      | Sauce Labs Fleece Jacket |
      | Sauce Labs Onesie        |
    And Open Shopping Cart Page
    And Shopping Cart Page Is Displayed
    And Validate Number of Items In Cart On Shopping Cart Page
    Then Validate Sub-Total Cost Of Items Purchased On Shopping Cart Page

    When User Begins Checkout
    And Checkout Information Page Is Displayed
    And Proceed From Checkout Information
    Then Validate Error Message Displayed On Checkout Information Page
      | True |

    When Cancel Checkout Information
    And User Begins Checkout
    And Validate Error Message Displayed On Checkout Information Page
      | False |
    Then Fill Checkout Information Page
      | First Name | Last Name | Postal Code |
      | Cire       | May       | underwater  |

    When Checkout Overview Page Is Displayed
    And Validate Number Of Products In Cart On Overview Page
    And Validate Sub-Total On Overview Page
    And Validate Tax Total On Overview Page
    And Validate Total On Overview Page
    Then Finish On Checkout Overview Page