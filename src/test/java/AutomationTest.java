import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutomationTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void cleanUp() {
        driver.quit();
    }

    @Test
    public void Test_1() {
        driver.get("https://www.google.ca");
        driver.findElement(By.xpath("(//input[@class='gNO89b'])[2]")).click();
    }
}
