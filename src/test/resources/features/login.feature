@login
Feature: Login
  As a user I should able to login into app

  @happypath
  Scenario: I login with valid credential
    Given I navigate to login page
    And I enter "tomsmith" as username
    And I enter "SuperSecretPassword!" as password
    When I click on login to submit
    Then I should see "You logged into a secure area!" on successful message of login
    When I click on logout
    Then I should see "You logged out of the secure area!" on successful message of logout

  @negative_login_cases
  Scenario Outline: I login with invalid credential
    Given I navigate to login page
    And I enter <username> as username
    And I enter <password> as password
    When I click on login to submit
    Then I should see <message> on failure message of login
    Examples:
      | title                         | username   | password               | message                          |
      | invalid password              | "tomsmith" | "SuperSecretPassword"  | "Your password is invalid!"      |
      | invalid username              | "omsmith"  | "SuperSecretPassword!" | "Your username is invalid!"      |
      | invalid username              | "omsmith"  | "SuperSecretPassword!" | "Your username is invalid!"      |
      | invalid username and password | "omsmith"  | "SuperSecretPassword"  | "Your username is invalid!"      |
      | long username                 | "omsmith"  | "SuperSecretPassword"  | "Your username is invalid!"      |