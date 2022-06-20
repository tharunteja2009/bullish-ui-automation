package com.bullish.steps;

import com.bullish.pages.PostLoginPage;
import com.bullish.pages.PreLoginPage;
import com.bullish.utils.WebPageFactory;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginSteps {
  static Logger logger = LoggerFactory.getLogger(LoginSteps.class);
  SoftAssertions softly = new SoftAssertions();

  PreLoginPage preLoginPage = (PreLoginPage) WebPageFactory.getPageObject(PreLoginPage.class);
  PostLoginPage postLoginPage = (PostLoginPage) WebPageFactory.getPageObject(PostLoginPage.class);

  @Given("^I navigate to login page$")
  public void i_navigate_to() {
    Assert.assertEquals(
        "title page of prelogin not matched", "Login Page", preLoginPage.getPreLoginTitle());
  }

  @When("^I enter \"([^\"]*)\" as username$")
  public void i_enter_into_input_userame(String username) {
    preLoginPage.enterUserName(username);
  }

  @When("^I enter \"([^\"]*)\" as password$")
  public void i_enter_into_input_password(String password) {
    preLoginPage.enterPassword(password);
  }

  @When("^I click on login to submit$")
  public void i_click_on_login() {
    preLoginPage.clickLogin();
  }

  @Then("^I should see \"([^\"]*)\" on successful message of login$")
  public void see_message(String message) {
    logger.info("Actual : {}", postLoginPage.getMessage());
    logger.info("Expected : {}", message);
    softly.assertThat(postLoginPage.getMessage()).contains(message);
    softly.assertAll();
  }

  @Then("^I should see \"([^\"]*)\" on failure message of login$")
  public void see_fail(String message) {
    logger.info("Actual : {}", preLoginPage.getErrorMessage());
    logger.info("Expected : {}", message);
    softly.assertThat(preLoginPage.getErrorMessage()).contains(message);
    softly.assertAll();
  }

  @When("^I click on logout$")
  public void click_logout() {
    postLoginPage.clickLogout();
  }

  @Then("I should see \"([^\"]*)\" on successful message of logout$")
  public void see_logout_message(String message) {
    logger.info("Actual : {}", preLoginPage.getMessage());
    logger.info("Expected : {}", message);
    softly.assertThat(preLoginPage.getMessage()).contains(message);
    softly.assertAll();
  }
}
