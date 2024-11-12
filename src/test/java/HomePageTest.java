import org.example.Pages.HomePage;
import org.example.Pages.LoginPage;
import org.junit.jupiter.api.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void Test_1() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("standard_user", "secret_sauce");

        HomePage homePage = new HomePage(this.driver);
        homePage.getProductByName("Sauce Labs Backpack");
    }
}
