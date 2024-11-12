package org.example.Pages.AbstractPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Base {
    protected WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
//        PageFactory.initElements(this.driver, this.getClass());
        PageFactory.initElements(driver, this);
    }
}
