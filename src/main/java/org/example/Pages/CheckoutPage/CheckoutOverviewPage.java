package org.example.Pages.CheckoutPage;

import org.example.Components.CartList;
import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends BasePage {
    @FindBy(className = "subheader")
    private WebElement pageTitle;

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

    private final String quantity = "summary_quantity";
    private final CartList cartList;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
        this.cartList = new CartList(driver, this.quantity);
    }

    public void waitForCheckoutOverviewPage() {
        this.waitForVisibilityOfElement(this.pageTitle);
    }

    public void clickFinishButton() {
        this.finishButton.click();
    }

    public void clickCancelButton() {
        this.cancelButton.click();
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
        return cartList.getCartSize();
    }

    public double calculateSubTotal() {
        return cartList.calculateSubTotal();
    }

    public double calculateTax() {
        double result = (this.getSummaryTotalLabel() - this.getSubTotalLabel()) / this.getSubTotalLabel();
        return (double) Math.round(result * 100) / 100;
    }

    public double calculateTaxAndSubTotal() {
        return (double) Math.round(this.calculateTax() * this.calculateSubTotal() * 100) / 100;
    }

    public double calculateTotal() {
        return cartList.calculateTotal();
    }
}