package org.example.Pages.ProductPage;

import org.example.Pages.AbstractPage.BasePage;
import org.example.Pages.HomePage.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {
    @FindBy(className = "btn_primary")
    private WebElement addToCartButton;

    @FindBy(className = "btn_secondary")
    private WebElement removeButton;
    @FindBy(className = "inventory_details_back_button")
    private WebElement backButton;

    Product product;

    public ProductPage(WebDriver driver, Product p) {
        super(driver);
        this.product = p;
    }

    public void clickAddToCartButton() {
        this.addToCartButton.click();
        this.waitForVisibilityOfElement(this.removeButton);
    }

    public void clickRemoveButton() {
        this.removeButton.click();
        this.waitForVisibilityOfElement(this.addToCartButton);
    }

    public void clickBackButton() {
        this.backButton.click();
    }
}
