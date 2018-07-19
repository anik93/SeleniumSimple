package com.selenium.test.pop;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;

public class PageObject {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected FluentWait fluentWait;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        webDriverWait = new WebDriverWait(driver, 60);
        fluentWait = new FluentWait(driver);
        fluentWait.ignoreAll(Arrays.asList(NoSuchElementException.class));
    }

}
