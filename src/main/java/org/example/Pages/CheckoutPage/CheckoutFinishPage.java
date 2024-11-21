package org.example.Pages.CheckoutPage;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFinishPage extends BasePage {

    @FindBy(className = "complete-header")
    WebElement headerMsg;
    @FindBy(className = "complete-text")
    WebElement verificationMsg;
    @FindBy(className = "pony_express")
    WebElement ponyExpressImg;
    private String expectedHeaderMsg = "THANK YOU FOR YOUR ORDER";
    private String expectedVerificationMsg = "Your order has been dispatched, and will arrive just as " +
            "fast as the pony can get there!";

    public CheckoutFinishPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyFinishPageDisplayed() {
        this.waitForVisibilityOfElement(this.ponyExpressImg);
        return this.headerMsg.getText().equals(this.expectedHeaderMsg)
                && this.verificationMsg.getText().equals(this.expectedVerificationMsg);
    }
}
