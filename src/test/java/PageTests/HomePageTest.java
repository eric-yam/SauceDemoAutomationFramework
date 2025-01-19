package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import TestScriptData.HomeTestData;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ExtensionBaseTest.class)
public class HomePageTest extends BaseTest {

    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#homePageTestDataProvider")
    public void Test_1(HomeTestData hptd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(hptd.getUsername(), hptd.getPassword());
        log.info("Logged in with valid username, password: [" + hptd.getUsername() + ", " + hptd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        boolean productFound = true;
        for (int i = 0; productFound && i < hptd.getExpectedProductNames().size(); i++) {
            productFound = productFound && (!homePage.getProductByName(hptd.getExpectedProductNames().get(i)).isEmpty());
        }

        assertTrue(productFound);
        log.info("Successfully validated all expected products exist on Home Page: " + hptd.getExpectedProductNames());
    }

    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#homePageTestDataProvider")
    public void Test_2(HomeTestData hptd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(hptd.getUsername(), hptd.getPassword());
        log.info("Logged in with valid username, password: [" + hptd.getUsername() + ", " + hptd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        homePage.applyFilter("Name (A to Z)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByNameAscend());
        log.info("Successfully validated HomePage now in ascending order by name");

        homePage.applyFilter("Name (Z to A)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByNameDescend());
        log.info("Successfully validated HomePage now in descending order by name");

        homePage.applyFilter("Price (low to high)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceAscend());
        log.info("Successfully validated HomePage now in ascending order by price");

        homePage.applyFilter("Price (high to low)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceDescend());
        log.info("Successfully validated HomePage now in descending order by price");
    }

    //Verify we can add items to cart through the homepage
    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#homePageTestDataProvider")
    public void Test_3(HomeTestData hptd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(hptd.getUsername(), hptd.getPassword());
        log.info("Logged in with valid username, password: [" + hptd.getUsername() + ", " + hptd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        for (String s : hptd.getProductsToSelect()) {
            homePage.clickProductAddToCart(s);
            log.info("Product: [" + s + "] on home page has been added to cart");
        }
    }
}
