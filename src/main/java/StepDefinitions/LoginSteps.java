package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    TestContext context;

    public LoginSteps(TestContext context) {
        this.context = context;
    }

    @Given("User Logs In:")
    public void userEntersLoginCredentials(DataTable dataTable) {
        List<Map<String, String>> table = dataTable.asMaps(String.class, String.class);

        LoginPage loginPage = new LoginPage(this.context.driver);
        for (Map<String, String> row : table) {
            loginPage.inputUserName(row.get("Username"));
            loginPage.inputPassword(row.get("Password"));

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
