package org.example.Pages;

import org.example.Pages.AbstractPage.BaseObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductCollector extends BaseObject {

    @FindBy(className = "inventory_item")
    public List<WebElement> webElProducts;

    private List<Products> productsList;

    public ProductCollector(WebDriver driver) {
        super(driver);
    }

    public void init() {
        this.productsList = new ArrayList<>();

        for (WebElement webElProduct : this.webElProducts) {
            String name = webElProduct.findElement(By.className("inventory_item_name")).getText();
            String priceStr = webElProduct.findElement(By.className("inventory_item_price")).getText();

            double price = Double.parseDouble(priceStr.replace("$", ""));

            Products p = new Products(this.driver, name, price);

            this.productsList.add(p);
        }
    }

    public List<Products> getProductsList() {
        return this.productsList;
    }
}
