package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;

import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    /*
     * Adding tags to Before and After annotations specifies that these methods will only
     * execute on feature files tagged with the specified name in the annotation
     */
//    @Before("@Login")
//    public void setup() {
//        this.context.setup();
//    }
//
//    @After("@Login")
//    public void tearDown(Scenario scenario) {
//        this.context.cleanUp(scenario);
//    }

    @Given("User Logs In:")
    public void userEntersLoginCredentials(DataTable dataTable) {
        Map<String, String> dt = dataTable.asMap();
        Map<String, String> map = new Hashtable<>(dt);

        LoginPage loginPage = new LoginPage(this.context.driver);

        for (Map.Entry<String, String> e : map.entrySet()) {
            loginPage.inputUserName(e.getKey());
            loginPage.inputPassword(e.getValue());
            loginPage.clickLoginButton();
        }
    }

    @Then("Validates user successfully logged in")
    public void loginSuccess() {
        HomePage homePage = new HomePage(this.context.driver);
        assertTrue(homePage.topNavigationBarDisplayed());
    }

    @Then("Validates user failed to log in")
    public void loginFailure() {
        LoginPage loginPage = new LoginPage(this.context.driver);
        assertTrue(loginPage.isErrorMsgDisplayed());
    }
}
