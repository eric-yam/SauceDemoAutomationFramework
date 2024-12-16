package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import org.PageObjects.Pages.CheckoutPage.CheckoutInformationPage;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.PageObjects.Pages.ShoppingCartPage.ShoppingCartPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ExtensionBaseTest.class)
public class ShoppingCartTest extends BaseTest {
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

    double expectedSubTotal = 113.95;
    String username = "standard_user";
    String password = "secret_sauce";

    //Test adding products to cart and then removing all products in cart
    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.username, this.password);
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, Arrays.asList(this.productsToSelect));
        log.info("Product: " + productsAddedLog + " added to shopping cart");
//        this.addProductsToCart(driver, this.productsToSelect);
        homePage.clickShoppingCart();

        //verify they've been added
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        assertEquals(productsToSelect.length, scp.getNumberOfProductsInCart());
        log.info("Successfully validated actual number of items in cart: [" + scp.getNumberOfProductsInCart() +
                "] match the expected number of items in cart: [" + productsToSelect.length + "]");

        ArrayList<String> productsRemoveLog = scp.removeProductsShoppingCart(driver, Arrays.asList(this.productsToRemove));
        log.info("Product: " + productsRemoveLog + " removed from shopping cart");
//        this.removeProductsShoppingCart(driver, productsToRemove);

        //verify they've been removed
        assertEquals(0, scp.getNumberOfProductsInCart());
        log.info("Successfully validated actual number of items in cart: [" + scp.getNumberOfProductsInCart() +
                "] match the expected number of items in cart: [" + 0 + "]");
    }

    //Test functionality of buttons on shopping cart page
    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.username, this.password);
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

//        String[] productsToSelect = {
//                "Sauce Labs Backpack",
//                "Sauce Labs Bike Light"
//        };

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, Arrays.asList(this.productsToSelect));
        log.info("Product: " + productsAddedLog + " added to shopping cart");
//        this.addProductsToCart(driver, productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        scp.clickContinueShoppingButton();
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        homePage.clickShoppingCart();
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        scp.clickCheckoutButton();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);
        cip.waitForCheckoutInformationPage();
        log.info("Landed on Checkout Information Page");
    }

    //Test cases for verifying total cost of items being purchased
    @Test
    public void Test_3() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(this.username, this.password);
        log.info("Logged in with valid username, password: [" + this.username + ", " + this.password + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, Arrays.asList(this.productsToSelect));
        log.info("Product: " + productsAddedLog + " added to shopping cart");
//        this.addProductsToCart(driver, productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        assertEquals(expectedSubTotal, scp.calculateSubTotal());
        log.info("Successfully validated actual calculated subtotal: [" + scp.calculateSubTotal() +
                "] match the expected calculated subtotal:[" + expectedSubTotal + "]");
    }

    //Helper Functions
//    public void addProductsToCart(WebDriver driver, String[] productsToSelect) {
//        HomePage homePage = new HomePage(driver);
//        for (String s : productsToSelect) {
//            Product p = homePage.clickProduct(s);
//            ProductPage pp = new ProductPage(driver, p);
//
//            pp.clickAddToCartButton();
//            log.info("Product: [" + s + "] added to shopping cart");
//            pp.clickBackButton();
//            homePage.waitForHomePage();
//        }
//    }

//    public void removeProductsShoppingCart(WebDriver driver, String[] productsToRemove) {
//        ShoppingCartPage scp = new ShoppingCartPage(driver);
//
//        for (String s : productsToRemove) {
//            scp.clickRemoveItem(s);
//            log.info("Product[" + s + "] removed from shopping cart");
//        }
//    }
}
