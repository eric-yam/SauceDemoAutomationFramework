package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ExtensionBaseTest.class)
public class LoginTests extends BaseTest {

//    @Random
//    private int temporary;
//    @Random
//    private static int temporary2;

//    @Test
    @ParameterizedTest
    @MethodSource("TestDataProvider#validLoginTestDataProvider")
    public void Test_1(Hashtable<String, String> loginData) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(loginData.get("username"));
        log.info("Successfully inputted valid username: [" + loginData.get("username") + "]");
        loginPage.inputPassword(loginData.get("password"));
        log.info("Successfully inputted valid password: [" + loginData.get("password") + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");
    }

    @ParameterizedTest
    @MethodSource("TestDataProvider#invalidLoginTestDataProvider")
    public void Test_2(Hashtable<String, String> loginData) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(loginData.get("username"));
        log.info("Successfully inputted invalid username: [" + loginData.get("username") + "]");
        loginPage.inputPassword(loginData.get("password"));
        log.info("Successfully inputted invalid password: [" + loginData.get("password") + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        assertTrue(loginPage.isErrorMsgDisplayed());
        log.info("Successfully displayed failed to login message");

        loginPage.clickClearErrorButton();
        assertFalse(loginPage.isErrorMsgDisplayed());
        log.info("Successfully cleared error message");
    }
}

