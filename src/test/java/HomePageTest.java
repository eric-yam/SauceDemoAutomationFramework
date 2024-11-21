import org.example.Pages.HomePage.HomePage;
import org.example.Pages.LoginPage.LoginPage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageTest extends BaseTest {
//    automation:
//    need to add logging
//    implement ci/cd
//    allure reporting
//    implement cucumber bdd
    @Test
    public void Test_1() {
        //Refactor to move test variables into separate test configuration file
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        String[] expectedProductNames = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        boolean productFound = true;
        for (int i = 0; productFound && i < expectedProductNames.length; i++) {
            productFound = productFound && (!homePage.getProductByName(expectedProductNames[i]).isEmpty());
        }

        assertTrue(productFound);
    }

    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        homePage.applyFilter("Name (A to Z)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByNameAscend());

        homePage.applyFilter("Name (Z to A)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByNameDescend());

        homePage.applyFilter("Price (low to high)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceAscend());

        homePage.applyFilter("Price (high to low)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceDescend());
    }

    //Verify we can add items to cart through the homespage
    @Test
    public void Test_3(){
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");
        String[] productsToSelect = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        HomePage homePage = new HomePage(this.driver);
//        homePage.clickProductAddToCart("Sauce Labs Backpack");

        for (String s : productsToSelect) {
            homePage.clickProductAddToCart(s);
        }

    }
}
