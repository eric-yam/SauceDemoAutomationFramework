package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import TestScriptData.LoginPageTestData;
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
    public void Test_1(LoginPageTestData lptd) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(lptd.getValidUsername());
        log.info("Successfully inputted valid username: [" + lptd.getValidUsername() + "]");
        loginPage.inputPassword(lptd.getValidPassword());
        log.info("Successfully inputted valid password: [" + lptd.getValidPassword() + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        //Verify login successful by validating we are on the homepage
        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");
    }

    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#loginTestDataProvider")
    public void Test_2(LoginPageTestData lptd) {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputUserName(lptd.getInvalidUsername());
        log.info("Successfully inputted invalid username: [" + lptd.getInvalidUsername() + "]");
        loginPage.inputPassword(lptd.getInvalidPassword());
        log.info("Successfully inputted invalid password: [" + lptd.getInvalidPassword() + "]");
        loginPage.clickLoginButton();
        log.info("Clicked Login button");

        assertTrue(loginPage.isErrorMsgDisplayed());
        log.info("Successfully displayed failed to login message");

        loginPage.clickClearErrorButton();
        assertFalse(loginPage.isErrorMsgDisplayed());
        log.info("Successfully cleared error message");
    }
}

