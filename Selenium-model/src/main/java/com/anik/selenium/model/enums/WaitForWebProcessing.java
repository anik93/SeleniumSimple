package com.anik.selenium.model.enums;

import com.anik.selenium.model.enums.interfaces.WaitForPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public enum WaitForWebProcessing implements WaitForPage {
    STANDARD {
        @Override
        public synchronized void wait(WebDriverWait webDriverWait) {
            webDriverWait.until((WebDriver driver) -> ((JavascriptExecutor) driver)
                    .executeScript("return document.readyState").equals("complete"));
        }
    },
    JQUERY {
        @Override
        public synchronized void wait(WebDriverWait webDriverWait) {
            webDriverWait.until((WebDriver driver) -> parseBoolean.test(driver,
                    "return (window.jQuery != null) && (jQuery.active === 0);"));
        }
    },
    ANGULAR {
        @Override
        public synchronized void wait(WebDriverWait webDriverWait) {
            webDriverWait.until((WebDriver driver) -> parseBoolean.test(driver,
                    "return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)"));
        }
    };

}
