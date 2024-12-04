package org.PageObjects.Pages.AbstractPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;
//    protected Logger log;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));
//        this.log = LogManager.getLogger(this);
//        PageFactory.initElements(this.driver, this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void waitForInvisibilityOfElement(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void waitForVisibilityOfElement(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }

    public void waitForTextToChange(WebElement el, String expectedStr) {
        wait.until(ExpectedConditions.textToBePresentInElement(el, expectedStr));
    }
}
