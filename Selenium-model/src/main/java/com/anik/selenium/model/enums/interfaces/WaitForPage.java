package com.anik.selenium.model.enums.interfaces;

import java.util.function.BiPredicate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface WaitForPage {

    public void wait(WebDriverWait webDriverWait);

    public BiPredicate<WebDriver, String> parseBoolean = (driver, query) -> Boolean
            .valueOf(((JavascriptExecutor) driver).executeScript(query).toString());
}
