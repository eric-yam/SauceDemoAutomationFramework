package org.example.Pages.HomePage;

import io.qameta.allure.Step;
import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {
    @FindBy(className = "product_label")
    WebElement pageTitle;

    @FindBy(className = "product_sort_container")
    WebElement filterButton;

    @FindBy(xpath = "//Select[@Class='product_sort_container']//option")
    List<WebElement> filterOptions;

    ProductCollector productCollector;

    public HomePage(WebDriver driver) {
        super(driver);
        this.setOrderOfProductList();
    }

    public void setOrderOfProductList() {
        // Subsequent updates to the order of product list must invoke this method again
        // To update ProductCollector for its instance in HomePage

        ProductCollector pc = new ProductCollector(this.driver);
        pc.init();
        this.productCollector = pc;
    }

    @Step("Apply Filter [{0}]")
    public void applyFilter(String filter) {
        this.filterButton.click();

        for (WebElement filterOption : this.filterOptions) {
            if (filterOption.getText().equals(filter)) {
                filterOption.click();
            }
        }
    }

    @Step("Wait For Home Page")
    public void waitForHomePage() {
        this.waitForVisibilityOfElement(this.pageTitle);
    }

    @Step("Add Product {0} From Home Page To Shopping Cart")
    public void clickProductAddToCart(String productName) {
        int index = this.indexOfProduct(productName);
        this.productCollector.clickAddProductToCart(index);
    }

    @Step("Click Product [{0}]")
    public Product clickProduct(String productName) {
        int index = this.indexOfProduct(productName);
        this.productCollector.clickProductByIndex(index);

        return this.productCollector.getProductsList().get(index);
    }

    public int indexOfProduct(String productName) {
        // If the product exists within ProductCollector, returns valid index
        // Otherwise returns -1 as invalid index

        List<Product> productList = this.productCollector.getProductsList();

        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getProductName().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    @Step("Get Product [{0}] By Product Name")
    public List<Product> getProductByName(String productName) {
        List<Product> result = new ArrayList<>();

        for (Product product : this.productCollector.getProductsList()) {
            if (product.getProductName().equals(productName)) {
                result.add(product);
            }
        }
        return result;
    }

    @Step("Verify Products In Ascending Order By Name")
    public boolean orderByNameAscend() {
        boolean isOrder = true;
        List<Product> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            int result = list.get(i).getProductName().compareTo(list.get(i + 1).getProductName());
            isOrder = isOrder && (result < 0);
        }
        return isOrder;
    }

    @Step("Verify Products In Descending Order By Name")
    public boolean orderByNameDescend() {
        boolean isOrder = true;
        List<Product> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            int result = list.get(i).getProductName().compareTo(list.get(i + 1).getProductName());
            isOrder = isOrder && (result > 0);
        }
        return isOrder;
    }

    @Step("Verify Products In Ascending Order By Price")
    public boolean orderByPriceAscend() {
        boolean isOrder = true;
        List<Product> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            isOrder = isOrder && (list.get(i).getProductPrice() <= list.get(i + 1).getProductPrice());
        }
        return isOrder;
    }

    @Step("Verify Products In Descending Order By Price")
    public boolean orderByPriceDescend() {
        boolean isOrder = true;
        List<Product> list = this.productCollector.getProductsList();

        for (int i = 0; isOrder && i < list.size() - 1; i++) {
            isOrder = isOrder && (list.get(i).getProductPrice() >= list.get(i + 1).getProductPrice());
        }
        return isOrder;
    }
}
