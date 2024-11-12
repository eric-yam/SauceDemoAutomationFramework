package org.example.Pages;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(className = "inventory_item")
    public List<WebElement> products;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean homeDisplayed() {
        return this.hamBurgMenuDisplayed() && this.shoppingCartDisplayed();
    }

    public List<WebElement> getProductByName(String productName) {
        List<WebElement> result = new ArrayList<WebElement>();

        for (WebElement product : this.products) {
            String label = product.findElement(By.className("inventory_item_name")).getText();
            if (label.equals(productName)) {
                result.add(product);
            }
        }
        return result;
    }
}
