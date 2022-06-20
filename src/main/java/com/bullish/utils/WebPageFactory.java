package com.bullish.utils;

import org.openqa.selenium.support.PageFactory;

public class WebPageFactory {

  public static Object getPageObject(Class pageObjectClass){
    return PageFactory.initElements(DriverUtil.getDefaultDriver(), pageObjectClass);
  }
}
