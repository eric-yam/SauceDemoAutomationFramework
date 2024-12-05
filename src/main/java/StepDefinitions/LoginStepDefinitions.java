package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;

import java.util.List;
import java.util.Map;

//import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {

    TestContext context;

    public LoginStepDefinitions(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup() {
        this.context.setup();
    }

    @After
    public void tearDown() {
        this.context.cleanUp();
    }

    @Given("User Logs In:")
    public void userEntersLoginCredentials(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);

        LoginPage loginPage = new LoginPage(this.context.driver);
        loginPage.inputUserName(rows.get(0).get("Username"));
        loginPage.inputPassword(rows.get(0).get("Password"));
        loginPage.clickLoginButton();
    }

    @Then("Validates user successfully logged in")
    public void loginSuccess() {
        HomePage homePage = new HomePage(this.context.driver);
        assert (homePage.topNavigationBarDisplayed());
    }

    @Then("Validates user failed to log in ")
    public void loginFailure() {
        LoginPage loginPage = new LoginPage(this.context.driver);
        assert (loginPage.isErrorMsgDisplayed());
    }

}
