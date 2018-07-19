package com.selenium.test.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ArticlePage extends HomePage {

    @FindBy(id = "firstHeading")
    private WebElement titleLabel;

    public ArticlePage(WebDriver driver) {
        super(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(titleLabel));
    }

    public boolean checkTitleOfArticle(String title) {
        return titleLabel.getText().equals(title);
    }
}
