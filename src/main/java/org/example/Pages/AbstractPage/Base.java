package org.example.Pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class Base {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Base(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(5));

//        PageFactory.initElements(this.driver, this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void waitForInvisibilityOfElement(WebElement el) {
        wait.until(ExpectedConditions.invisibilityOf(el));
    }

    public void waitForVisibilityOfElement(WebElement el) {
        wait.until(ExpectedConditions.visibilityOf(el));
    }
}
