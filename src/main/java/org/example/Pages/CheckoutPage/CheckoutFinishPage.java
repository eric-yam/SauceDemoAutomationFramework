package org.example.Pages.CheckoutPage;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutFinishPage extends BasePage {
    private static final String EXPECTED_HEADER_MSG = "THANK YOU FOR YOUR ORDER";
    private static final String EXPECTED_VERIFICATION_MSG = "Your order has been dispatched, and will arrive just as " +
            "fast as the pony can get there!";

    @FindBy(className = "subheader")
    private WebElement pageTitle;

    @FindBy(className = "complete-header")
    WebElement headerMsg;

    @FindBy(className = "complete-text")
    WebElement verificationMsg;

    @FindBy(className = "pony_express")
    WebElement ponyExpressImg;

    public CheckoutFinishPage(WebDriver driver) {
        super(driver);
    }

    public void waitCheckoutFinishPage() {
        this.waitForVisibilityOfElement(this.pageTitle);
    }

    public boolean verifyFinishPageDisplayed() {
        this.waitForVisibilityOfElement(this.ponyExpressImg);
        return this.headerMsg.getText().equals(EXPECTED_HEADER_MSG)
                && this.verificationMsg.getText().equals(EXPECTED_VERIFICATION_MSG);
    }
}
