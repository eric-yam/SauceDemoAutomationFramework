import org.example.Pages.HomePage.HomePage;
import org.example.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests extends BaseTest {
    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.inputUserName("standard_user");
        loginPage.inputPassword("secret_sauce");
        loginPage.clickLoginButton();

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(this.driver);
        assertTrue(homePage.topNavigationBarDisplayed());

    }

    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(this.driver);

        loginPage.inputUserName("standard_user");
        loginPage.inputPassword("invalid_password");
        loginPage.clickLoginButton();
        assertTrue (loginPage.isErrorMsgDisplayed());

        loginPage.clickClearErrorButton();
        assertFalse(loginPage.isErrorMsgDisplayed());

    }
}

