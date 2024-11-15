import org.example.Pages.HomePage.HomePage;
import org.example.Pages.HomePage.Product;
import org.example.Pages.LoginPage.LoginPage;
import org.example.Pages.ProductPage.ProductPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class HomePageTest extends BaseTest {

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

        assert (productFound == true);
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

    @Test
    public void Test_3() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        String[] productsToSelect = {
                "Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

        this.addProductsToCart(this.driver, productsToSelect);
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
}
