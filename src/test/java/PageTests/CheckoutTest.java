package PageTests;

import PageTests.Extensions.ExtensionBaseTest;
import PageTests.TestBase.BaseTest;
import TestScriptData.CheckoutPageTestData;
import org.PageObjects.Pages.CheckoutPage.CheckoutFinishPage;
import org.PageObjects.Pages.CheckoutPage.CheckoutInformationPage;
import org.PageObjects.Pages.CheckoutPage.CheckoutOverviewPage;
import org.PageObjects.Pages.HomePage.HomePage;
import org.PageObjects.Pages.LoginPage.LoginPage;
import org.PageObjects.Pages.ShoppingCartPage.ShoppingCartPage;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ExtensionBaseTest.class)
public class CheckoutTest extends BaseTest {
    @ParameterizedTest
    @MethodSource("TestScriptData.TestDataProvider#checkoutTestDataProvider")
    public void Test_1(CheckoutPageTestData cptd) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(cptd.getUsername(), cptd.getPassword());
        log.info("Logged in with valid username, password: [" + cptd.getUsername() + ", " + cptd.getPassword() + "]");

        HomePage homePage = new HomePage(driver);
        homePage.waitForHomePage();
        assertTrue(homePage.topNavigationBarDisplayed());
        log.info("Successfully logged in and landed on HomePage");

        ArrayList<String> productsAddedLog = homePage.addProductProductPage(driver, cptd.getProductsToSelect());
        log.info("Product: " + productsAddedLog + " added to shopping cart");
//        cptd.addProductsToCart(driver, cptd.productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(driver);
        scp.waitForShoppingCartPage();
        log.info("Landed on Shopping Cart Page");

        assertEquals(scp.getNumberOfProductsInCart(), cptd.getProductsToSelect().size());
        log.info("Successfully validated actual cart size: [" + scp.getNumberOfProductsInCart() +
                "] matches expected cart size: [" + cptd.getProductsToSelect().size() + "]");

        assertEquals(scp.calculateSubTotal(), cptd.getExpectedSubTotal());
        log.info("Successfully validated actual subtotal: [" + scp.calculateSubTotal() +
                "] matches expected subtotal: [" + cptd.getExpectedSubTotal() + "]");
        scp.clickCheckoutButton();

        CheckoutInformationPage cip = new CheckoutInformationPage(driver);
        cip.waitForCheckoutInformationPage();
        log.info("Landed on Checkout Information Page");
        cip.clickContinueButton();

        assertTrue(cip.isErrorMsgDisplayed());
        log.info("Successfully validated error message displayed on Checkout Information Page");

        cip.clickCancelButton();
        scp.clickCheckoutButton();

        assertFalse(cip.isErrorMsgDisplayed());
        log.info("Successfully validated error message not displayed on Checkout Information Page");

        cip.setInputFirstName(cptd.getFirstName());
        cip.setInputLastName(cptd.getLastName());
        cip.setInputPostalCode(cptd.getPostalCode());
        log.info("Entered Checkout Information: [" + cptd.getFirstName() + ", " +
                cptd.getLastName() + ", " + cptd.getPostalCode() + "] ");

        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(driver);
        cop.waitForCheckoutOverviewPage();
        log.info("Landed on Checkout Overview Page");

        assertEquals(cop.getNumberOfProductsInCart(), cptd.getProductsToSelect().size());
        log.info("Successfully validated actual cart size: [" + scp.getNumberOfProductsInCart() +
                "] matches expected cart size: [" + cptd.getProductsToSelect().size() + "]");

        assertEquals(cop.getSubTotalLabel(), cop.calculateSubTotal());
        log.info("Successfully validated actual subtotal: [" + cop.calculateSubTotal() +
                "] matches expected subtotal: [" + cop.getSubTotalLabel() + "]");

        assertEquals(cop.getSummaryTaxTotalLabel(), cop.calculateTaxAndSubTotal());
        log.info("Successfully actual tax total: [" + cop.calculateTaxAndSubTotal() +
                "] matches expected subtotal: [" + cop.getSummaryTaxTotalLabel() + "]");

        assertEquals(cop.getSummaryTotalLabel(), cop.calculateTotal());
        log.info("Successfully actual total summary: [" + cop.calculateTotal() +
                "] matches expected total summary: [" + cop.getSummaryTotalLabel() + "]");

        cop.clickFinishButton();

        CheckoutFinishPage cfp = new CheckoutFinishPage(driver);
        cfp.waitCheckoutFinishPage();
        log.info("Landed on Checkout Finish Page");

        assertTrue(cfp.verifyFinishPageDisplayed());
        log.info("Successfully validated Checkout Finish Page is displayed");
    }
}
