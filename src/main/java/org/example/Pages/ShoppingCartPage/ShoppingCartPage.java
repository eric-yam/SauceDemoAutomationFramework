package org.example.Pages.ShoppingCartPage;

import org.example.Components.CartList;
import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    private final String quantity = "cart_quantity";
    private final String removeButton = "cart_button";
    //    private final String removeButton = ".//button[@class='btn_secondary cart_button']";
    private final CartList cartList;
    @FindBy(xpath = "//div//descendant::a[@class='btn_secondary']")
    private WebElement continueShoppingButton;
    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
        this.cartList = new CartList(driver, this.quantity);
    }

    public void clickContinueShoppingButton() {
        this.continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        this.checkoutButton.click();
    }

    public void clickRemoveItem(String productName) {
        List<WebElement> cartListElements = this.cartList.getCartList();
        int index = this.getIndexOfCartItemByName(productName);

        cartListElements.get(index).findElement(By.className(this.removeButton)).click();
//        this.cartList.get(index).findElement(By.xpath(this.removeButton)).click();
    }

    public int getIndexOfCartItemByName(String productName) {
        return this.cartList.getIndexOfCartItemByName(productName);
    }

    public int getNumberOfProductsInCart() {
        return this.cartList.getCartSize();
    }

    public double calculateSubTotal() {
        return this.cartList.calculateSubTotal();
    }
}
