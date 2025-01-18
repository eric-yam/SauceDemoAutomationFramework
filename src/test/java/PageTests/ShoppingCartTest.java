package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import TestScriptData.ShoppingCartTestData;
import org.PageObjects.Pages.CheckoutPage.CheckoutInformationPage;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.PageObjects.Pages.ShoppingCartPage.ShoppingCartPage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ExtensionBaseTest.class)
public class ShoppingCartTest extends BaseTest {
    //Test adding products to cart and then removing all products in cart
    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#shoppingCartTestDataProvider")
    public void Test_1(ShoppingCartTestData sctd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(sctd.getUsername(), sctd.getPassword());
        log.info("Logged in with valid username, password: [" + sctd.getUsername() + ", " + sctd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, sctd.getProductsToSelect());
        log.info("Product: " + productsAddedLog + " added to shopping cart");
        homePage.clickShoppingCart();

        //verify they've been added
        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        assertEquals(sctd.getProductsToSelect().size(), scp.getNumberOfProductsInCart());
        log.info("Successfully validated actual number of items in cart: [" + scp.getNumberOfProductsInCart() +
                "] match the expected number of items in cart: [" + sctd.getProductsToSelect().size() + "]");

        ArrayList<String> productsRemoveLog = scp.removeProductsShoppingCart(driver, sctd.getProductsToRemove());
        log.info("Product: " + productsRemoveLog + " removed from shopping cart");

        //verify they've been removed
        assertEquals(0, scp.getNumberOfProductsInCart());
        log.info("Successfully validated actual number of items in cart: [" + scp.getNumberOfProductsInCart() +
                "] match the expected number of items in cart: [" + 0 + "]");
    }

    //Test functionality of buttons on shopping cart page
    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#shoppingCartTestDataProvider")
    public void Test_2(ShoppingCartTestData sctd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(sctd.getUsername(), sctd.getPassword());
        log.info("Logged in with valid username, password: [" + sctd.getUsername() + ", " + sctd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, sctd.getProductsToSelect());
        log.info("Product: " + productsAddedLog + " added to shopping cart");
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
    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#shoppingCartTestDataProvider")
    public void Test_3(ShoppingCartTestData sctd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(sctd.getUsername(), sctd.getPassword());
        log.info("Logged in with valid username, password: [" + sctd.getUsername() + ", " + sctd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        log.info("Landed on Home Page");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, sctd.getProductsToSelect());
        log.info("Product: " + productsAddedLog + " added to shopping cart");
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        assertEquals(sctd.getExpectedSubTotal(), scp.calculateSubTotal());
        log.info("Successfully validated actual calculated subtotal: [" + scp.calculateSubTotal() +
                "] match the expected calculated subtotal: [" + sctd.getExpectedSubTotal() + "]");
    }
}