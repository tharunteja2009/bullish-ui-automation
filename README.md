Framework Assignment for UI Applocation
--------------
Running test
--------------

Go to your project directory from terminal and hit following commands
* `mvn clean test (defualt will run on local chrome browser)`
* `mvn clean test -Dbrowser=firex (to run in firefox browser)`
* `mvn clean test -Dcucumber.filter.tags="@login" (to run in specific tag of feature)`


--------------
Configuration
--------------

- config vm arg is for loading different kinds of desired capabilities of browser from properties placed at /src/main/java/resources/browserConfigs
- browser vm arg for selection of browser for execution by run time , by default pointed to chrome.
- broser specifi drivers based on versions are placed at /src/main/java/resources/drivers.
- tag configured by default at runner level **@login**
- screenshot will be captured on failure of scenario
- Please download drivers based on browser version of system and place at /src/main/java/resources/drivers for successful execution.
   - GeckoDriver for firefox  https://github.com/mozilla/geckodriver/releases
   - ChromeDriver for chrome  http://chromedriver.storage.googleapis.com/index.html

