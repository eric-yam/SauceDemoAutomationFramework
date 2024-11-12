import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTest {
    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.inputUserName("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(this.driver);
        assert(homePage.homeDisplayed() == true);

    }

    @Test
    public void Test_2(){
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.inputUserName("standard_user");
        loginPage.inputPassword("invalid_password");
        loginPage.clickLoginButton();
        assert(loginPage.isErrorMsgDisplayed() == true);

        loginPage.clickClearErrorButton();
        assert(loginPage.isErrorMsgDisplayed() == false);

    }
}

