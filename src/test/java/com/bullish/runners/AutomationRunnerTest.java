package com.bullish.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    plugin = {
        "pretty", "html:target/cucumberHtmlReport",
        "html:target/cucumberHtmlReport",     //  for html result
        "pretty:target/cucumber-json-report.json"   // for json result
    },
    features = "classpath:features",
    glue = "com.bullish.steps" ,// user step definitions package
    tags = {"@login"}
)

public class AutomationRunnerTest {
}
