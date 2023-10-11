Feature: verify login functionality

  Background:

  Scenario: user logs in with correct credentials
    Given correct username"Aika"
    And correct password"Abs123!"
    When user clicks login button
    Then user logs in

  Scenario: user logs in with correct credentials
    Given incorrect username"Aika"
    And incorrect password"Abs123!"
    When user clicks login button
    Then user logs in



    @firstOutline
  Scenario Outline:
    Given the user is on the login page
    When correct "<username>" username
    And correct "<password>" password
    And user is clicking login button
    Then verify user logs in

    Examples:
      | username    | password  |
      | Aika        | abc123    |
      | Harrypotter | xyz456    |
      | Barbie      | canHello1 |