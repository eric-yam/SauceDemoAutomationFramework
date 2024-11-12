import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

public class
HomePageTest extends BaseTest {

    @Test
    public void Test_1() {
        //Refactor to move test variables into separate test configuration file
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        String[] temp = {"Sauce Labs Backpack",
                "Sauce Labs Bike Light",
                "Sauce Labs Bolt T-Shirt",
                "Sauce Labs Fleece Jacket",
                "Sauce Labs Onesie"
        };

//        homePage.getProductByName("Sauce Labs Backpack");
        boolean productFound = true;
        for (int i = 0; productFound && i < temp.length; i++) {
            productFound = productFound && (homePage.getProductByName(temp[i]).size() > 0);
        }

        assert (productFound == true);
    }

    @Test
    public void Test_2() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        homePage.applyFilter("Price (high to low)");

        //Verify filter has been applied by validating order of items
        assert(this.validateFilterApplied(this.driver) == true);

    }

    public boolean validateFilterApplied(WebDriver driver) {
        return false;
    }
}
