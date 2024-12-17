package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.PageObjects.Pages.HomePage.HomePage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeSteps {

    TestContext context;

    String[] expectedProductNames = {
            "Sauce Labs Backpack",
            "Sauce Labs Bike Light",
            "Sauce Labs Bolt T-Shirt",
            "Sauce Labs Fleece Jacket",
            "Sauce Labs Onesie"
    };

    public HomeSteps(TestContext context) {
        this.context = context;
    }

    @And("Home Page is Displayed")
    public void landedHomePage() {
        HomePage homePage = new HomePage(this.context.driver);
        homePage.waitForHomePage();
        assertTrue(homePage.topNavigationBarDisplayed());

        boolean productFound = true;
        for (int i = 0; productFound && i < this.expectedProductNames.length; i++) {
            productFound = productFound && (!homePage.getProductByName(this.expectedProductNames[i]).isEmpty());
        }

        assertTrue(productFound);
    }

    @And("Add Products To Cart Via Shortcut On Home Page")
    public void addToCart(DataTable dataTable) {
        List<String> productsToSelect = dataTable.asList();

        HomePage homePage = new HomePage(this.context.driver);
        assertTrue(homePage.topNavigationBarDisplayed());

        for (String s : productsToSelect) {
            homePage.clickProductAddToCart(s);
        }
        this.context.numPurchasedItems += productsToSelect.size();
    }

    @And("Add Products To Shopping Cart Via Product Page")
    public void addProducts(DataTable dataTable) {
        List<String> productsToSelect = dataTable.asList();

        HomePage homePage = new HomePage(this.context.driver);
        ArrayList<String> productsAddedList = homePage.addProductProductPage(this.context.driver, productsToSelect);
        this.context.numPurchasedItems += productsAddedList.size();
    }

    @When("User Applies Filter On Home Page And Validates Filter Applied")
    public void applyFilter(DataTable dataTable) {
        List<String> filterOptionList = dataTable.asList();

        HomePage homePage = new HomePage(this.context.driver);

        for (String filterOption : filterOptionList) {
            homePage.applyFilter(filterOption);
            homePage.setOrderOfProductList();

            if (filterOption.equals("Name (A to Z)")) {
                homePage.orderByNameAscend();
                assertTrue(homePage.orderByNameAscend());
            } else if (filterOption.equals("Name (Z to A)")) {
                homePage.orderByNameDescend();
                assertTrue(homePage.orderByNameDescend());
            } else if (filterOption.equals("Price (low to high)")) {
                homePage.orderByPriceAscend();
                assertTrue(homePage.orderByPriceAscend());
            } else if (filterOption.equals("Price (high to low)")) {
                homePage.orderByPriceDescend();
                assertTrue(homePage.orderByPriceDescend());
            }
        }
    }

    @And("Open Shopping Cart Page")
    public void openShoppingCartPage() {
        HomePage homePage = new HomePage(this.context.driver);
        homePage.clickShoppingCart();
    }
}
