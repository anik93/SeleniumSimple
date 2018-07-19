package com.selenium.test.pop;

import org.openqa.selenium.WebDriver;

public class ReceiptPage extends PageObject {

    public ReceiptPage(WebDriver driver) {
        super(driver);
    }

    public String confirmationCurrentPage() {
        return driver.getCurrentUrl().split("#")[1];
    }

}
