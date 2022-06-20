package com.bullish.pages;

import com.bullish.actions.WebActions;
import com.bullish.utils.Constants;
import com.bullish.utils.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PreLoginPage {
  WebDriver driver;

  public PreLoginPage(WebDriver driver) {
    this.driver = driver;
  }

  @FindBy(xpath = "//h2")
  WebElement preloginTitle;

  @FindBy(xpath = "//input[@name='username']")
  WebElement userNameTxt;

  @FindBy(xpath = "//input[@name='password']")
  WebElement passwordTxt;

  @FindBy(xpath = "//button[@class='radius']")
  WebElement loginBtn;

  @FindBy(xpath = "//div[@class='flash error']")
  WebElement errorLabel;

  @FindBy(xpath = "//*[@id='flash' and @class='flash success']")
  WebElement messageLabel;

  public String getPreLoginTitle() {
    return WebActions.getElementText(preloginTitle);
  }

  public void enterUserName(String input) {
    WebActions.sendTextToTextBox(userNameTxt, input);
  }

  public void enterPassword(String input) {
    WebActions.sendTextToTextBox(passwordTxt, input);
  }

  public void clickLogin() {
    WebActions.clickElement(loginBtn);
  }

  public String getErrorMessage() {
    return WebActions.getElementText(errorLabel);
  }

  public String getMessage() {
    return WebActions.getElementText(messageLabel);
  }
}
