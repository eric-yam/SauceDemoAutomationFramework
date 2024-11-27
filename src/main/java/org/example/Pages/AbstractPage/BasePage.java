package org.example.Pages.AbstractPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage extends Base {

    @FindBy(xpath = "//div[@class='bm-burger-button']//button")
    private WebElement hamBurgMenu;

    @FindBy(xpath = "//*[local-name()='svg']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMsg;

    public BasePage(WebDriver driver) {
        super(driver);
    }

    public void clickHamBurgMenu() {
        this.hamBurgMenu.click();
    }

    public void clickShoppingCart() {
        this.shoppingCart.click();
    }

    public boolean hamBurgMenuDisplayed() {
        try {
            this.hamBurgMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean shoppingCartDisplayed() {
        try {
            this.shoppingCart.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean topNavigationBarDisplayed() {
        return this.hamBurgMenuDisplayed() && this.shoppingCartDisplayed();
    }

    public boolean isErrorMsgDisplayed() {
        try {
            errorMsg.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
