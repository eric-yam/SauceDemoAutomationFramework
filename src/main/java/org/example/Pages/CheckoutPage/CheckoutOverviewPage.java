package org.example.Pages.CheckoutPage;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CheckoutOverviewPage extends BasePage {
    private final String quantity = "summary_quantity";
    private final String productName = "inventory_item_name";
    private final String productPrice = "inventory_item_price";

    private final double tax = 1.08;

    @FindBy(className = "cart_item")
    List<WebElement> cartList;

    @FindBy(className = "btn_action")
    private WebElement finishButton;
    @FindBy(className = "cart_cancel_link")
    private WebElement cancelButton;

    @FindBy(className = "summary_subtotal_label")
    private WebElement summarySubTotal;
    @FindBy(className = "summary_tax_label")
    private WebElement summaryTaxTotal;
    @FindBy(className = "summary_total_label")
    private WebElement summaryTotal;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public void clickFinishButton() {
        this.finishButton.click();
    }

    public void clickCancelButton() {
        this.cancelButton.click();
    }

    public int getIndexOfCartItemByName(String productName) {
        for (int i = 0; i < this.cartList.size(); i++) {
            WebElement item = this.cartList.get(i);
            if (item.findElement(By.className(this.productName)).getText().equals(productName)) {
                return i;
            }
        }
        return -1;
    }

    public String getLabelValue(WebElement element) {
        String value = element.getText();
        value = value.substring(value.indexOf("$") + 1, value.indexOf(".") + 3);
        return value;
    }

    public double getSubTotalLabel() {
        //need to take out number value from string label
        return Double.parseDouble(this.getLabelValue(this.summarySubTotal));
    }

    public double getSummaryTaxTotalLabel() {
        return Double.parseDouble(this.getLabelValue(this.summaryTaxTotal));
    }

    public double getSummaryTotalLabel() {
        return Double.parseDouble(this.getLabelValue(this.summaryTotal));
    }

    public int getNumberOfProductsInCart() {
        return this.cartList.size();
    }

    public double calculateSubTotal() {
        double result = 0;
        for (int i = 0; i < this.cartList.size(); i++) {
            String temp = this.cartList.get(i).findElement(By.className(this.productPrice)).getText();
            double price = Double.parseDouble(temp.replace("$", ""));
            int quantity = Integer.parseInt(this.cartList.get(i).findElement(By.className(this.quantity)).getText());
            result += price * quantity;
        }
        return result;
    }

    public double calculateTax() {
        double result = (this.getSummaryTotalLabel() - this.getSubTotalLabel()) / this.getSubTotalLabel();
        return (double) Math.round(result * 100) / 100;
    }

    public double calculateTaxAndSubTotal() {
        return (double) Math.round(this.calculateTax() * this.calculateSubTotal() * 100) / 100;
    }

    public double calculateTotal() {
        double result = Math.round(this.calculateSubTotal() * this.tax * 100);
        return result / 100;
    }
}