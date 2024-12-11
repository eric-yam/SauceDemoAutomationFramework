package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ExtensionBaseTest.class)
public class HomePageTest extends BaseTest {
    String[] expectedProductNames = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie"
    };

    String[] productsToSelect = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie"
    };
    String username = "standard_user";
    String password = "secret_sauce";

    @Test
    public void Test_1() {
        //Refactor to move test variables into separate test configuration file
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.username, this.password);
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        boolean productFound = true;
        for (int i = 0; productFound && i < this.expectedProductNames.length; i++) {
            productFound = productFound && (!homePage.getProductByName(this.expectedProductNames[i]).isEmpty());
        }

        assertTrue(productFound);
        log.info("Successfully validated all expected products exist on Home Page: " +
                Arrays.toString(this.expectedProductNames));
    }

    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

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
    @Test
    public void Test_3() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.username, this.password);
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

        HomePage homePage = new HomePage(driver);
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        for (String s : this.productsToSelect) {
            homePage.clickProductAddToCart(s);
            log.info("Product: [" + s + "] on home page has been added to cart");
        }
    }
}
