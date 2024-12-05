package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import org.PageObjects.Pages.HomePage.HomePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeSteps {

    TestContext context;

    String[] expectedProductNames = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie"
    };

    String[] productsToSelect = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie"
    };

    public HomeSteps(TestContext context) {
        this.context = context;
    }

    @Before("@Home")
    public void setup() {
        this.context.setup();
    }

    @After("@Home")
    public void tearDown() {
        this.context.cleanUp();
    }

    @Then("Home Page is Displayed")
    public void landedHomePage() {
        HomePage homePage = new HomePage(this.context.driver);
        homePage.waitForHomePage();
        assertTrue(homePage.topNavigationBarDisplayed());

        boolean productFound = true;
        for (int i = 0; productFound && i < this.expectedProductNames.length; i++) {
            productFound = productFound && (!homePage.getProductByName(this.expectedProductNames[i]).isEmpty());
        }

        assertTrue(productFound);
    }
}
