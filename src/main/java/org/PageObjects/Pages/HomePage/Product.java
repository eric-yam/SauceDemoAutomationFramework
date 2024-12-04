package org.PageObjects.Pages.HomePage;

import org.PageObjects.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.WebDriver;

public class Product extends BaseObject {
    private final String productName;
    private final double productPrice;

    public Product(WebDriver driver, String name, double price) {
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
