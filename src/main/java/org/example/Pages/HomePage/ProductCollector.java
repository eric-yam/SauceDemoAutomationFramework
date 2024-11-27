package org.example.Pages.HomePage;

import org.example.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class ProductCollector extends BaseObject {
    //Locators
    @FindBy(className = "inventory_item")
    public List<WebElement> webElProducts;

    private final String inventoryItems = "inventory_item";
    private final String productName = "inventory_item_name";
    private final String productPrice = "inventory_item_price";
    private final String productImg = "inventory_item_img";
    private final String addToCartButton = "btn_primary";

    //Product
    private List<Product> productsList;

    public ProductCollector(WebDriver driver) {
        super(driver);
    }

    public void init() {
        this.productsList = new ArrayList<>();

        for (WebElement webElProduct : this.webElProducts) {
            String name = webElProduct.findElement(By.className(this.productName)).getText();
            String priceStr = webElProduct.findElement(By.className(this.productPrice)).getText();

            double price = Double.parseDouble(priceStr.replace("$", ""));

            Product p = new Product(this.driver, name, price);

            this.productsList.add(p);
        }
    }

    public void clickProductByIndex(int index) {
        WebElement el = this.webElProducts.get(index);
        el.findElement(By.className(this.productImg)).click();
        this.waitForInvisibilityOfElement(By.className(this.inventoryItems));
    }

    public void clickAddProductToCart(int index) {
        WebElement el = this.webElProducts.get(index).findElement(By.className(this.addToCartButton));
        el.click();
        this.waitForTextToChange(el, "REMOVE");
    }

    public List<Product> getProductsList() {
        return this.productsList;
    }
}
