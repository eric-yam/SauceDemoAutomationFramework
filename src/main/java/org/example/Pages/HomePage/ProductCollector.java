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

    @FindBy(className = "inventory_item")
    public List<WebElement> webElProducts;

    private List<Product> productsList;

    public ProductCollector(WebDriver driver) {
        super(driver);
    }

    public void init() {
        this.productsList = new ArrayList<>();

        for (WebElement webElProduct : this.webElProducts) {
            String name = webElProduct.findElement(By.className("inventory_item_name")).getText();
            String priceStr = webElProduct.findElement(By.className("inventory_item_price")).getText();

            double price = Double.parseDouble(priceStr.replace("$", ""));

            Product p = new Product(this.driver, name, price);

            this.productsList.add(p);
        }
    }

    public void clickProductByIndex(int index) {
        WebElement el = this.webElProducts.get(index);
        el.findElement(By.className("inventory_item_img")).click();
        this.waitForInvisibilityOfElement(el);
    }

    public List<Product> getProductsList() {
        return this.productsList;
    }
}
