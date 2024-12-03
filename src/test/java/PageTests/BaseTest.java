package PageTests;

import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    public static WebDriver driver;
    public LoggerWrapper log;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log = new LoggerWrapper(driver);

        //Opens Web
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterEach
    public void cleanUp() {
//        Allure.addAttachment("Console log: ", String.valueOf(driver.manage().logs().get(LogType.BROWSER).getAll()));
//        LogEntries logEntries = driver.manage().logs().get(LogType.DRIVER);
//        Allure.addAttachment("Console log: ", String.valueOf(logEntries));

        //Add attachment to allure report at the end of each test
        Allure.addAttachment("Console log:", String.valueOf(log.getTestConsoleLog()));
        driver.quit();
    }

    //TODO: Open Allure report automatically after execution
    //TODO: Take screenshot whenever a failure occurs
    //TODO: Add allure steps annotations
}
