package StepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import org.PageObjects.Pages.ShoppingCartPage.ShoppingCartPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingCartSteps {

    TestContext context;

    public ShoppingCartSteps(TestContext context) {
        this.context = context;
    }

    @And("Shopping Cart Page Is Displayed")
    public void shoppingCarPageDisplayed() {
        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
        scp.waitForShoppingCartPage();
    }

    @And("Validate Number of Items In Cart On Shopping Cart Page")
    public void validateNumItemsInCart() {
        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
        assertEquals(scp.getNumberOfProductsInCart(), this.context.numPurchasedItems);
    }

    @And("Remove Products In Shopping Cart")
    public void removeProducts(DataTable dataTable) {
        List<String> productsToRemove = dataTable.asList();

        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
//        this.removeProductsShoppingCart(this.context.driver, productsToRemove);
        ArrayList<String> removedProductsList = scp.removeProductsShoppingCart(this.context.driver, productsToRemove);
        this.context.numPurchasedItems -= removedProductsList.size();

        assertEquals(0, scp.getNumberOfProductsInCart());
    }

    @And("User Returns To Home Page From Shopping Cart Page")
    public void continueShoppingButton() {
        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
        scp.clickContinueShoppingButton();
    }

    @And("User Begins Checkout")
    public void beginCheckout() {
        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
        scp.clickCheckoutButton();
    }

    @And("Validate Sub-Total Cost Of Items Purchased Is Expected {double} On Shopping Cart Page")
    public void validateTotalItemsPurchased(double expectedSubTotal) {
        ShoppingCartPage scp = new ShoppingCartPage(this.context.driver);
        scp.waitForShoppingCartPage();

        assertEquals(expectedSubTotal, scp.calculateSubTotal());
    }
}