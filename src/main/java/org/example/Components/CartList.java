package org.example.Components;

import org.example.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartList extends BaseObject {

    private final String quantity;
    private final String productName = "inventory_item_name";
    private final String productPrice = "inventory_item_price";

    private final double tax = 1.08;
    @FindBy(className = "cart_item")
    List<WebElement> cartList;

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
            if (item.findElement(By.className(this.productName)).getText().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    public double calculateSubTotal() {
        double result = 0;
        for (int i = 0; i < this.cartList.size(); i++) {
            String temp = this.cartList.get(i).findElement(By.className(this.productPrice)).getText();
            double price = Double.parseDouble(temp.replace("$", ""));
            int quantity = Integer.parseInt(this.cartList.get(i).findElement(By.className(this.quantity)).getText());
            result += price * quantity;
        }
        return result;
    }

    public double calculateTotal() {
        double result = Math.round(this.calculateSubTotal() * this.tax * 100);
        return result / 100;
    }
}
