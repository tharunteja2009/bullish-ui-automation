package com.bullish.steps;

import com.bullish.utils.DriverUtil;
import com.bullish.utils.PropertyManager;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks {

  static Logger logger = LoggerFactory.getLogger(Hooks.class);

  @After
  public void afterScenario(Scenario scenario) {
    if (scenario.isFailed()) {
      final byte[] screenshot =
          ((TakesScreenshot) DriverUtil.getDefaultDriver()).getScreenshotAs(OutputType.BYTES);
      scenario.embed(screenshot, "image/png");
    }

    DriverUtil.closeDriver();
    logger.info("application closed successfully");
  }

  @Before
  public void beforeScenario(Scenario scenario) {
    //close browser window if still exist
    DriverUtil.closeDriver();
    //initialize driver and launch app url
    DriverUtil.getDefaultDriver().get(PropertyManager.APPURL);
    logger.info("application launched successfully");
  }
}
