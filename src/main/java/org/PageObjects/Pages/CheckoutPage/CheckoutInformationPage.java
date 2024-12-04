package org.PageObjects.Pages.CheckoutPage;

import org.PageObjects.Pages.AbstractPage.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInformationPage extends BasePage {
    @FindBy(className = "subheader")
    private WebElement pageTitle;

    @FindBy(id = "first-name")
    private WebElement inputFirstName;

    @FindBy(id = "last-name")
    private WebElement inputLastName;

    @FindBy(id = "postal-code")
    private WebElement inputPostalCode;

    @FindBy(className = "cart_cancel_link")
    private WebElement cancelButton;

    @FindBy(className = "cart_button")
    private WebElement continueButton;

    public CheckoutInformationPage(WebDriver driver) {
        super(driver);
    }

    public void waitForCheckoutInformationPage() {
        this.waitForVisibilityOfElement(this.pageTitle);
    }

    public void clickCancelButton() {
        this.cancelButton.click();
    }

    public void clickContinueButton() {
        this.continueButton.click();
    }

    public void setInputFirstName(String fn) {
        this.inputFirstName.sendKeys(fn);
    }

    public void setInputLastName(String ln) {
        this.inputLastName.sendKeys(ln);
    }

    public void setInputPostalCode(String pc) {
        this.inputPostalCode.sendKeys(pc);
    }
}