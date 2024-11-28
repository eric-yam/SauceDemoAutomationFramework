package org.example.Pages.HomePage;

import org.example.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductCollector extends BaseObject {
    private static final String INVENTORY_ITEMS = "inventory_item";
    private static final String PRODUCT_NAME = "inventory_item_name";
    private static final String PRODUCT_PRICE = "inventory_item_price";
    private static final String PRODUCT_IMG = "inventory_item_img";
    private static final String ADD_TO_CART_BUTTON = "btn_primary";

    //Locators
    @FindBy(className = "inventory_item")
    public List<WebElement> webElProducts;

    //Product
    private List<Product> productsList;

    public ProductCollector(WebDriver driver) {
        super(driver);
    }

    public void init() {
        this.productsList = new ArrayList<>();

        for (WebElement webElProduct : this.webElProducts) {
            String name = webElProduct.findElement(By.className(PRODUCT_NAME)).getText();
            String priceStr = webElProduct.findElement(By.className(PRODUCT_PRICE)).getText();

            double price = Double.parseDouble(priceStr.replace("$", ""));

            Product p = new Product(this.driver, name, price);

            this.productsList.add(p);
        }
    }

    public void clickProductByIndex(int index) {
        WebElement el = this.webElProducts.get(index);
        el.findElement(By.className(PRODUCT_IMG)).click();
        this.waitForInvisibilityOfElement(By.className(INVENTORY_ITEMS));
    }

    public void clickAddProductToCart(int index) {
        WebElement el = this.webElProducts.get(index).findElement(By.className(ADD_TO_CART_BUTTON));
        el.click();
        this.waitForTextToChange(el, "REMOVE");
    }

    public List<Product> getProductsList() {
        return this.productsList;
    }
}
