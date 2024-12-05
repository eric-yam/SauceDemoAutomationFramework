Feature: Example Feature

  Scenario: : Successful Login Test
    Given User Logs In:
      | Username      | Password     |
      | standard_user | secret_sauce |
    Then Validates user successfully logged in

  Scenario: : Failed Login Test
    Given User Logs In:
      | Username      | Password     |
      | standard_user | invalid_pass |
    Then Validates user successfully logged in