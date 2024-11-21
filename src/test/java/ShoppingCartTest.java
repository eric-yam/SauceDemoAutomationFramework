import org.example.Pages.HomePage.HomePage;
import org.example.Pages.HomePage.Product;
import org.example.Pages.LoginPage.LoginPage;
import org.example.Pages.ProductPage.ProductPage;
import org.example.Pages.ShoppingCartPage.ShoppingCartPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartTest extends BaseTest {

    //Test adding products to cart and then removing all products in cart
    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();

        String[] productsToSelect = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        String[] productsToRemove = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        this.addProductsToCart(this.driver, productsToSelect);
        //verify they've been added
        homePage.clickShoppingCart();
        ShoppingCartPage scp = new ShoppingCartPage(this.driver);
        assertEquals(scp.getNumberOfProductsInCart(), productsToSelect.length);

        this.removeProductsShoppingCart(this.driver, productsToRemove);
        //verify they've been removed
        assertEquals(scp.getNumberOfProductsInCart(), 0);
    }

    //Test functionality of buttons on shopping cart page
    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();

        String[] productsToSelect = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light"
        };

        this.addProductsToCart(this.driver, productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(this.driver);
        scp.clickContinueShoppingButton();
        homePage.clickShoppingCart();
        scp.clickCheckoutButton();
    }

    //Test cases for verifying total cost of items being purchased
    @Test
    public void Test_3() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();

        String[] productsToSelect = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        double expectedSubTotal = 113.95;

        this.addProductsToCart(this.driver, productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(this.driver);
        assertEquals(scp.calculateSubTotal(), expectedSubTotal);
    }

    //Helper Functions
    public void addProductsToCart(WebDriver driver, String[] productsToSelect) {
        HomePage homePage = new HomePage(driver);
        for (String s : productsToSelect) {
            Product p = homePage.clickProduct(s);
            ProductPage pp = new ProductPage(driver, p);

            pp.clickAddToCartButton();
            pp.clickBackButton();
            homePage.waitForHomePage();
        }
    }

    public void removeProductsShoppingCart(WebDriver driver, String[] productsToRemove) {
        ShoppingCartPage scp = new ShoppingCartPage(this.driver);

        for (String s : productsToRemove) {
            scp.clickRemoveItem(s);
        }
    }
}
