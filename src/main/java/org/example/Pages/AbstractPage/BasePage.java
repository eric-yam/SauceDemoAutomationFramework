package org.example.Pages.AbstractPage;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    @FindBy(xpath = "//div[@class='bm-burger-button']//button")
    private WebElement hamBurgMenu;
    @FindBy(xpath = "//*[local-name()='svg']")
    private WebElement shoppingCart;
    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
//        PageFactory.initElements(this.driver, this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void clickHamBurgMenu() {
        this.hamBurgMenu.click();
    }

    public boolean hamBurgMenuDisplayed() {
        try {
            this.hamBurgMenu.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickShoppingCart() {
        this.shoppingCart.click();
    }

    public boolean shoppingCartDisplayed() {
        try {
            this.shoppingCart.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
