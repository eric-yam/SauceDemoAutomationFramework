@Login
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

#  $ mvn clean test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.plugin=pretty -Dcucumber.plugin=io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm -Dcucumber.features=src/test/resources/features/Login.feature:4
