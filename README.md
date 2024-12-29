# SauceDemoAutomationFramework

## Summary
This automation framework tests the functionality of the SauceDemo web application by simulating user activities through test scripts and feature files. The automation framework is built using Java, Selenium, and JUnit 5, while incorporating design patterns such as: Page Object Model, Page Factory, and Dependency Injection. Through these design patterns, the web application page objects and test cases are decoupled from the framework. Therefore, the framework's setup can be reused to automate other web applications, while also capable of extending and adapting to the specific testing requirements for each application.

## Project Setup
1. Download the latest version of Java as the programming language
2. Download the latest version of Apache Maven binary zip file
   https://maven.apache.org/download.cgi
  
4. Download the latest version of Allure as our tool for test reporting (Install via archive)
   https://github.com/allure-framework/allure2/releases/tag/2.32.0

5. Download Jenkins WAR file
   https://www.jenkins.io/download/

## Allure
Allure is configured for JUnit 5 for test reporting. The Allure results directory is configured as: ```target/allure-results```. Additionally, Allure reporting is also integrated with Jenkins such that the results directory located relative to the workspace at: ```target/allure-results```

For the test scripts, Allure tracks and reports back the Selenium actions that succeed or fail, including an attachment with the logged actions performed during the test. For the feature files, Allure tracks and reports the step definitions that succeed or failed. In the event a test step fails, a screenshot is taken and added as an attachment in the Allure report.

To view the Allure report, navigate to the ```target``` directory within the project and use the ```allure serve``` command to view the Allure report.

## Logging
Logging is managed by a separate wrapper class called ```LoggerWrapper``` that wraps the ```Logger``` class from ```log4j```. At the start of a test's lifecycle, an instance of ```LoggerWrapper``` is instantiated and a log message is appenede to the ```consoleLog``` field for each test step. Once the test completes, the contents of the ```consoleLog``` string are added as an attachment to the Allure report.

## Test Execution
Executing all test scripts: ```mvn clean test```

Execute specific test script: ```mvn clean -Dtest=[Test Class Name] test```

Execute feature file: ```mvn clean test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.features=src/test/resources/features/[Feature File Name].feature```

Execute specific scenario from feature file: ```mvn clean test -Dsurefire.includeJUnit5Engines=cucumber -Dcucumber.features=src/test/resources/features/[Feature File Name].feature:[Line Number of Scenario]```


