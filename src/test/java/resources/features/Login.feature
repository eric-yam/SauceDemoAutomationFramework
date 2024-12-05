Feature: Login Tests

  Scenario: : Successful Login Test
    Given User Logs In:
      | standard_user | secret_sauce |
    Then Validates user successfully logged in

  Scenario: : Failed Login Test
    Given User Logs In:
      | standard_user | invalid_pass |
      | invalid_user  | secret_sauce |
      | invalid       | invalid_user |
    Then Validates user failed to log in