package org.PageObjects.Components;

import org.PageObjects.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartList extends BaseObject {
    private static final String PRODUCT_NAME = "inventory_item_name";
    private static final String PRODUCT_PRICE = "inventory_item_price";
    private static final double TAX = 1.08;

    @FindBy(className = "cart_item")
    List<WebElement> cartList;

    private final String quantity;

    public CartList(WebDriver driver, String cartName) {
        super(driver);
        this.quantity = cartName;
    }

    public List<WebElement> getCartList() {
        return this.cartList;
    }

    public int getCartSize() {
        return cartList.size();
    }

    public int getIndexOfCartItemByName(String productName) {
        for (int i = 0; i < this.cartList.size(); i++) {
            WebElement item = this.cartList.get(i);
            if (item.findElement(By.className(PRODUCT_NAME)).getText().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    public double calculateSubTotal() {
        double result = 0;
        for (WebElement element : this.cartList) {
            String temp = element.findElement(By.className(PRODUCT_PRICE)).getText();
            double price = Double.parseDouble(temp.replace("$", ""));
            int quantity = Integer.parseInt(element.findElement(By.className(this.quantity)).getText());
            result += price * quantity;
        }
        return result;
    }

    public double calculateTotal() {
        double result = Math.round(this.calculateSubTotal() * TAX * 100);
        return result / 100;
    }
}
