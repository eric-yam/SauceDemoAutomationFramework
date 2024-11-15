package org.example.Pages.LoginPage;

import org.example.Pages.AbstractPage.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

public class LoginPage extends BasePage {

    //Locators
    @FindBy(id = "user-name")
    private WebElement username;

    //Note that Findbys use a parent-child relationship
    //thus we find them in parent-child order
    //and all locators must be valid for locator to be valid
    @FindBys({
            @FindBy(className = "login-box"),
            @FindBy(id = "password")
    })
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(xpath = "//h3[@data-test='error']")
    private WebElement errorMsg;

    @FindBy(className = "error-button")
    private WebElement clearErrorButton;

    //Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        this.inputUserName(username);
        this.inputPassword(password);
        this.clickLoginButton();
    }

    public void inputUserName(String userNameValue) {
        this.username.sendKeys(userNameValue);
    }

    public void inputPassword(String passwordValue) {
        this.password.sendKeys(passwordValue);
    }

    public void clickLoginButton() {
        this.loginButton.click();
    }

    public void clickClearErrorButton() {
        this.clearErrorButton.click();
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
