package org.example.Pages.ShoppingCartPage;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ShoppingCartPage extends BasePage {
    private final String quantity = "cart_quantity";
    private final String productName = "inventory_item_name";
    private final String productPrice = "inventory_item_price";
    private final String removeButton = "cart_button";
//    private final String removeButton = ".//button[@class='btn_secondary cart_button']";

    @FindBy(className = "cart_item")
    List<WebElement> cartList;
    @FindBy(xpath = "//div//descendant::a[@class='btn_secondary']")
    private WebElement continueShoppingButton;
    @FindBy(className = "checkout_button")
    private WebElement checkoutButton;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public void clickContinueShoppingButton() {
        this.continueShoppingButton.click();
    }

    public void clickCheckoutButton() {
        this.checkoutButton.click();
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

    public void clickRemoveItem(String productName) {
        int index = this.getIndexOfCartItemByName(productName);
        this.cartList.get(index).findElement(By.className(this.removeButton)).click();
//        this.cartList.get(index).findElement(By.xpath(this.removeButton)).click();
    }

    public int getNumberOfProductsInCart() {
        return this.cartList.size();
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
}
