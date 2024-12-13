package StepDefinitions;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/*
 * This class is used for Cucumber Step Definitions
 *
 * This class is a Dependency class used by PicoContainer such that it's purpose is to
 * encapsulate the context of objects (In this case, retain the context of the webdriver).
 * This allows the state of these objects to shared among the other step definitions methods or
 * step definition classes
 *
 * Note that this is referred to as a Dependency class, meaning before a StepDefinition class is ran,
 * PicoContainer will create an instance of TestContext and pass the SAME instance/instances to its
 * StepDefinition's constructor
 * */
public class TestContext {

    protected WebDriver driver;
    int numPurchasedItems;

    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //Opens Web
        driver.get("https://www.saucedemo.com/v1/");
    }

    public void cleanUp(Scenario scenario) {
        this.takeScreenshotOnFailure(scenario);
        driver.quit();
    }

    public void takeScreenshotOnFailure(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] src = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(src, "image/png", "screenshot");
        }
    }
}