package org.PageObjects.Pages.ShoppingCartPage;

import io.qameta.allure.Step;
import org.PageObjects.Components.CartList;
import org.PageObjects.Pages.AbstractPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage {
    private static final String QUANTITY = "cart_quantity";
    private static final String REMOVE_BUTTON = "cart_button";

    @FindBy(xpath = "//div//descendant::a[@class='btn_secondary']")
    private WebElement continueShoppingButton;

    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    @FindBy(className = "subheader")
    private WebElement pageTitle;

    private final CartList cartList;

    //    private final String removeButton = ".//button[@class='btn_secondary cart_button']";

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.cartList = new CartList(driver, QUANTITY);
    }

    @Step("Wait For Shopping Cart Page")
    public void waitForShoppingCartPage() {
        this.waitForVisibilityOfElement(this.pageTitle);
    }

    @Step("Click Continue Shopping Button")
    public void clickContinueShoppingButton() {
        this.continueShoppingButton.click();
    }

    @Step("Click Checkout Button")
    public void clickCheckoutButton() {
        this.checkoutButton.click();
    }

    @Step("Remove Product [{0}] From Shopping Cart")
    public void clickRemoveItem(String productName) {
        List<WebElement> cartListElements = this.cartList.getCartList();
        int index = this.cartList.getIndexOfCartItemByName(productName);

        cartListElements.get(index).findElement(By.className(REMOVE_BUTTON)).click();
//        this.cartList.get(index).findElement(By.xpath(this.removeButton)).click();
    }

    @Step("Remove Product {1} on Shopping Cart Page")
    public ArrayList<String> removeProductsShoppingCart(WebDriver driver, String[] productsToRemove) {
        ArrayList<String> resultLog = new ArrayList<>();

        for (String s : productsToRemove) {
            this.clickRemoveItem(s);
//            log.info("Product[" + s + "] removed from shopping cart");
            resultLog.add(s);
        }
        return resultLog;
    }

    @Step("Get Number Of Products In Shopping Cart")
    public int getNumberOfProductsInCart() {
        return this.cartList.getCartSize();
    }

    @Step("Calculate Sub-Total In Shopping Cart")
    public double calculateSubTotal() {
        return this.cartList.calculateSubTotal();
    }
}
