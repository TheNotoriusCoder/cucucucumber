Feature: all login test scenarios are in this file

  Background:
    Given user navigate to login page

  @Login
  Scenario:
    When user enters email "admin@codewise.com" to the email field
    And user enters password "codewise123" to the password field
    Then user performs click action on the login button
    Then user should be logged in to the application

  @LoginNegative
  Scenario Outline: user tries to login with invalid credentials
    When user enters email "<email>" to the email field
    And user enters password "<password>" to the password field
    Then user performs click action on the login button
    Then user should not be logged in
    Examples:
      | email          | password |
      | barbie12@gmail | abc123   |
      | huisos@gmail   | shjfba34 |
      | sindy@gmail    | ghfg     |
      | john12         | john123  |

