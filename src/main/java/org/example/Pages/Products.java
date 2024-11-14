package org.example.Pages;

import org.example.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.WebDriver;

public class Products extends BaseObject {
    private final String productName;
    private final double productPrice;

    public Products(WebDriver driver, String name, double price) {
        super(driver);
        this.productName = name;
        this.productPrice = price;
    }

    public String getProductName() {
        return this.productName;
    }

    public double getProductPrice() {
        return this.productPrice;
    }
}
