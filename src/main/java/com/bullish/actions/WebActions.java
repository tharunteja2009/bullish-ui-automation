package com.bullish.actions;

import com.bullish.utils.Constants;
import com.bullish.utils.DriverUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebActions {
  static Logger logger = LoggerFactory.getLogger(WebActions.class);

  public static String getElementText(WebElement element) {
    waitAndGetElementByXpathSelector(
        DriverUtil.getDefaultDriver(), element, Constants.EXPLICIT_WAIT);
    return element.getText();
  }

  public static void clickElement(WebElement element) {
    waitAndGetElementByXpathSelector(
        DriverUtil.getDefaultDriver(), element, Constants.EXPLICIT_WAIT);
    element.click();
    logger.info("click on element : {}", element);
  }

  public static void sendTextToTextBox(WebElement element, String input) {
    waitAndGetElementByXpathSelector(
        DriverUtil.getDefaultDriver(), element, Constants.EXPLICIT_WAIT);
    element.sendKeys(input);
    logger.info("input to on element : {}", input);
    logger.info("element details: {}", element);
  }

  public static WebElement waitAndGetElementByXpathSelector(
      WebDriver driver, WebElement selector, int seconds) {
    return (new WebDriverWait(driver, seconds)).until(ExpectedConditions.visibilityOf(selector));
  }
}
