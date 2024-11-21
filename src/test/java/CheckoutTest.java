import org.example.Pages.CheckoutPage.CheckoutFinishPage;
import org.example.Pages.CheckoutPage.CheckoutInformationPage;
import org.example.Pages.CheckoutPage.CheckoutOverviewPage;
import org.example.Pages.HomePage.HomePage;
import org.example.Pages.HomePage.Product;
import org.example.Pages.LoginPage.LoginPage;
import org.example.Pages.ProductPage.ProductPage;
import org.example.Pages.ShoppingCartPage.ShoppingCartPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutTest extends BaseTest {

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

        double expectedSubTotal = 113.95;

        this.addProductsToCart(this.driver, productsToSelect);
        homePage.clickShoppingCart();

        ShoppingCartPage scp = new ShoppingCartPage(this.driver);
        assertEquals(scp.getNumberOfProductsInCart(), productsToSelect.length);
        assertEquals(scp.calculateSubTotal(), expectedSubTotal);
        scp.clickCheckoutButton();

        CheckoutInformationPage cip = new CheckoutInformationPage(this.driver);
        cip.clickContinueButton();
        assertTrue(cip.isErrorMsgDisplayed());

        cip.clickCancelButton();
        scp.clickCheckoutButton();

        assertFalse(cip.isErrorMsgDisplayed());
        cip.setInputFirstName("Cire");
        cip.setInputLastName("May");
        cip.setInputPostalCode("underwater");

        cip.clickContinueButton();

        CheckoutOverviewPage cop = new CheckoutOverviewPage(this.driver);
        assertEquals(cop.getNumberOfProductsInCart(), productsToSelect.length);
        assertEquals(cop.getSubTotalLabel(), cop.calculateSubTotal());
        assertEquals(cop.getSummaryTaxTotalLabel(), cop.calculateTaxAndSubTotal());
        assertEquals(cop.getSummaryTotalLabel(), cop.calculateTotal());

        cop.clickFinishButton();

        CheckoutFinishPage cfp = new CheckoutFinishPage(this.driver);
        assertTrue(cfp.verifyFinishPage());
    }

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
}
