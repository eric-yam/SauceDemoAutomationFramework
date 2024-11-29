import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {
    static WebDriver driver;
    Logger log;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        log = LogManager.getLogger(this);

        //Opens Web
        driver.get("https://www.saucedemo.com/v1/");
    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    //TODO: Open Allure report automatically after execution
    //TODO: Take screenshot whenever a failure occurs
    //TODO: Add allure steps annotations
}
