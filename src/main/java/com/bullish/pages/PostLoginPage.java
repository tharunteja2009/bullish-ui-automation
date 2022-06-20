package com.bullish.pages;

import com.bullish.actions.WebActions;
import com.bullish.utils.Constants;
import com.bullish.utils.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PostLoginPage {
  WebDriver driver;

  public PostLoginPage(WebDriver driver) {
    this.driver = driver;
  }

  @FindBy(xpath = "//div[@class='flash success']")
  WebElement messageLabel;

  @FindBy(xpath = "//a[@href='/logout']")
  WebElement logoutBtn;

  public String getMessage() {
    return WebActions.getElementText(messageLabel);
  }

  public void clickLogout() {
    WebActions.clickElement(logoutBtn);
  }
}
