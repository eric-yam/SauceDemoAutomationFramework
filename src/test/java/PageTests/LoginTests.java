package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ExtensionBaseTest.class)
public class LoginTests extends BaseTest {

    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    String invalidUsername = "invalid_user";
    String invalidPassword = "invalid_password";

//    @Random
//    private int temporary;
//    @Random
//    private static int temporary2;

    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(this.validUsername);
        log.info("Successfully inputted valid username: [" + this.validUsername + "]");
        loginPage.inputPassword(this.validPassword);
        log.info("Successfully inputted valid password: [" + this.validPassword + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");
    }

    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(this.invalidUsername);
        log.info("Successfully inputted invalid username: [" + this.invalidUsername + "]");
        loginPage.inputPassword(this.invalidPassword);
        log.info("Successfully inputted invalid password: [" + this.invalidPassword + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        assertTrue(loginPage.isErrorMsgDisplayed());
        log.info("Successfully displayed failed to login message");

        loginPage.clickClearErrorButton();
        assertFalse(loginPage.isErrorMsgDisplayed());
        log.info("Successfully cleared error message");
    }
}

