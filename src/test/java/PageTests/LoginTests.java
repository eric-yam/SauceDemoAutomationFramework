package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import TestScriptData.LoginTestData;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

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
    @MethodSource("TestScriptData.TestDataProvider#loginTestDataProvider")
    public void Test_1(LoginTestData lptd) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(lptd.getValidUser().get("username"));
        log.info("Successfully inputted valid username: [" + lptd.getValidUser().get("username") + "]");
        loginPage.inputPassword(lptd.getValidUser().get("password"));
        log.info("Successfully inputted valid password: [" + lptd.getValidUser().get("password") + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");
    }

    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#loginTestDataProvider")
    public void Test_2(LoginTestData lptd) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(lptd.getInvalidUser().get("username"));
        log.info("Successfully inputted invalid username: [" + lptd.getInvalidUser().get("username") + "]");
        loginPage.inputPassword(lptd.getInvalidUser().get("password"));
        log.info("Successfully inputted invalid password: [" + lptd.getInvalidUser().get("password") + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        assertTrue(loginPage.isErrorMsgDisplayed());
        log.info("Successfully displayed failed to login message");

        loginPage.clickClearErrorButton();
        assertFalse(loginPage.isErrorMsgDisplayed());
        log.info("Successfully cleared error message");
    }
}

