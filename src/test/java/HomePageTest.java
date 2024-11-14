import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.junit.jupiter.api.Test;

public class
HomePageTest extends BaseTest {

    @Test
    public void Test_1() {
        //Refactor to move test variables into separate test configuration file
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        String[] expectedProductNames = {"Sauce Labs Backpack",
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
        assert (homePage.orderByNameAscend() == true);

        homePage.applyFilter("Name (Z to A)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByNameDescend() == true);

        homePage.applyFilter("Price (low to high)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceAscend() == true);

        homePage.applyFilter("Price (high to low)");
        homePage.setOrderOfProductList();
        assert (homePage.orderByPriceDescend() == true);
    }
}
