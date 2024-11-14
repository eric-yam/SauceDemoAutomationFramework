package org.example.Pages;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(className = "product_sort_container")
    WebElement filterButton;
    @FindBy(xpath = "//Select[@Class='product_sort_container']//option")
    List<WebElement> filterOptions;
    ProductCollector productCollector;

    public HomePage(WebDriver driver) {
        super(driver);
        this.setOrderOfProductList();
    }

    public void applyFilter(String filter) {
        this.filterButton.click();

        for (WebElement filterOption : this.filterOptions) {
            if (filterOption.getText().equals(filter)) {
                filterOption.click();
            }
        }
    }

    public void setOrderOfProductList() {
        // Subsequent updates to the order of product list must invoke this method again
        // To update ProductCollector for instance of HomePage

        ProductCollector pc = new ProductCollector(this.driver);
        pc.init();
        this.productCollector = pc;
    }

    public boolean homeDisplayed() {
        return this.hamBurgMenuDisplayed() && this.shoppingCartDisplayed();
    }

    public List<Products> getProductByName(String productName) {
        List<Products> result = new ArrayList<>();

        for (Products product : this.productCollector.getProductsList()) {
            if (product.getProductName().equals(productName)) {
                result.add(product);
            }
        }
        return result;
    }

    public boolean orderByNameAscend() {
        boolean isOrder = true;
        List<Products> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            int result = list.get(i).getProductName().compareTo(list.get(i + 1).getProductName());
            isOrder = isOrder && (result < 0);
        }
        return isOrder;
    }

    public boolean orderByNameDescend() {
        boolean isOrder = true;
        List<Products> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            int result = list.get(i).getProductName().compareTo(list.get(i + 1).getProductName());
            isOrder = isOrder && (result > 0);
        }
        return isOrder;
    }

    public boolean orderByPriceAscend() {
        boolean isOrder = true;
        List<Products> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            isOrder = isOrder && (list.get(i).getProductPrice() <= list.get(i + 1).getProductPrice());
        }
        return isOrder;
    }

    public boolean orderByPriceDescend() {
        boolean isOrder = true;
        List<Products> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            isOrder = isOrder && (list.get(i).getProductPrice() >= list.get(i + 1).getProductPrice());
        }
        return isOrder;
    }
}
