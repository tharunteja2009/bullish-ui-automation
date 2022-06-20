package com.bullish.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DriverUtil {

  public static long DEFAULT_WAIT = 20;
  protected static WebDriver driver = null;
  static String currentPath = System.getProperty("user.dir");
  static Properties prop = new Properties();
  static DesiredCapabilities capability = null;
  static Logger logger = LoggerFactory.getLogger(DriverUtil.class);

  public static DesiredCapabilities getCapability(InputStream input) {
    DesiredCapabilities capability = new DesiredCapabilities();
    try {
      prop.load(input);
      if (prop.containsKey("app")) {
        String appName = prop.getProperty("app");
        if (!appName.contains("sauce-storage")) {
          String appPath = currentPath + "/src/main/java/appUnderTest/" + appName;
          prop.setProperty("app", appPath);
        }
      }

      // set capabilities
      Enumeration<Object> enuKeys = prop.keys();
      while (enuKeys.hasMoreElements()) {
        String key = (String) enuKeys.nextElement();
        String value = prop.getProperty(key);
        capability.setCapability(key, value);
      }
      input.close();
    } catch (Exception e) {
      logger.error(e.getCause().getMessage());
      logger.error(e.getMessage());
    }
    return capability;
  }

  public static WebDriver getDefaultDriver() {
    if (driver != null) {
      return driver;
    }
    //default configuration is Mac_chrome, can override via vm args using -Dconfig
    String config = System.getProperty("config", "Mac_chrome");

    if (!config.isEmpty()) {
      try {
        InputStream input =
            new FileInputStream(
                currentPath
                    + "/src/main/java/resources/browserConfigurations"
                    + config
                    + ".properties");
        capability = getCapability(input);
      } catch (Exception e) {
        logger.error(
            "\nException : File not present or Invalid config file name " + config + ".properties");
        logger.error(e.getMessage());
      }
    }
    driver = chooseDriver();
    driver.manage().timeouts().setScriptTimeout(DEFAULT_WAIT, TimeUnit.SECONDS);
    driver.manage().window().maximize();
    return driver;
  }

  private static WebDriver chooseDriver() {
    capability = new DesiredCapabilities();
    capability.setJavascriptEnabled(true);
    capability.setCapability("takesScreenshot", true);
    String preferredDriver =
        System.getProperty("browse") != null ? System.getProperty("browse") : "chrome";
    switch (preferredDriver.toLowerCase()) {
      case "safari":
        try {
          driver = new SafariDriver(capability);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
      case "edge":
        try {
          driver = new EdgeDriver(capability);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
      case "chrome":
        System.setProperty(
            "webdriver.chrome.driver", currentPath + "/src/main/resources/drivers/chromedriver");
        final ChromeOptions chromeOptions = new ChromeOptions();
        capability.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        try {
          driver = new ChromeDriver(capability);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
      default:
        System.setProperty(
            "webdriver.gecko.driver", currentPath + "/src/main/resources/drivers/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        capability.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
        try {
          driver = new FirefoxDriver(capability);
        } catch (Exception e) {
          logger.error(e.getMessage());
        }
        return driver;
    }
  }

  public static WebElement waitAndGetElementByXpathSelector(
      WebDriver driver, WebElement selector, int seconds) {
    return (new WebDriverWait(driver, seconds)).until(ExpectedConditions.visibilityOf(selector));
  }

  public static void closeDriver() {
    if (driver != null) {
      try {
        driver.close();
      } catch (Exception exception) {
        driver = null;
      }
    }
  }
}
