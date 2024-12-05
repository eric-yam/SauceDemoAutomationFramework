@Home
Feature: Home Page Tests

  Scenario: Successfully Landed On Home Page
    Given User Logs In:
      | standard_user | secret_sauce |
    And Validates user successfully logged in
    And Home Page is Displayed