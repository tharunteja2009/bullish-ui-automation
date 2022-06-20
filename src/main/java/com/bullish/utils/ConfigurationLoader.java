package com.bullish.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfigurationLoader
{
  static Logger logger = LoggerFactory.getLogger(ConfigurationLoader.class);

  static Properties prop = new Properties();
  static InputStream inputStream;

  static {
    loadProperties();
  }

  public static String getProperty(String property) {
    return prop.getProperty(property);
  }

  /**
   * this method used for loading application properties such as host url or any application credentials or any metadata
   */
  public static void loadProperties() {
      inputStream = ConfigurationLoader.class.getClassLoader().getResourceAsStream("application.properties");
    if (prop.isEmpty()) {
      try {
        prop.load(inputStream);
      } catch (FileNotFoundException e) {
        logger.error("no config file found");
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          inputStream.close();
        } catch (IOException io) {
          logger.error("unable to close input stream of config file");
        }
      }
    }
  }
}
