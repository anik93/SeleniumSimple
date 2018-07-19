package com.selenium.test.pop;

import java.util.function.BiPredicate;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionWebElement {

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return parseBoolean.test(driver,
                        "return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)");
            }
        };
    }

    public static ExpectedCondition<Boolean> jQueryAJAXCallsHaveCompleted() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return parseBoolean.test(driver, "return (window.jQuery != null) && (jQuery.active === 0);");
            }
        };
    }

    private static BiPredicate<WebDriver, String> parseBoolean = (driver, query) -> Boolean
            .valueOf(((JavascriptExecutor) driver).executeScript(query).toString());

    public static void angularHasFinishedProcessing(WebDriverWait webDriverWait) {
        webDriverWait.until((WebDriver driver) -> parseBoolean.test(driver,
                "return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)"));
    }

    public static void jQueryAJAXCallsHaveCompleted(WebDriverWait webDriverWait) {
        webDriverWait.until((WebDriver driver) -> parseBoolean.test(driver,
                "return (window.jQuery != null) && (jQuery.active === 0);"));
    }

    public static void waitForPageToLoad(WebDriverWait webDriverWait) {
        webDriverWait.until((WebDriver driver) -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

}
