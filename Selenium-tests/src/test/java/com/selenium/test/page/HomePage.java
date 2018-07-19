package com.selenium.test.page;

import org.openqa.selenium.WebDriver;

import com.selenium.test.pop.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends PageObject {

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(id = "searchButton")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@id='p-logo']/a")
    private WebElement logoImage;

    public HomePage(WebDriver driver) {
        super(driver);
        webDriverWait.until(ExpectedConditions.visibilityOf(searchButton));
    }

    public void setTextIntoSearchInput(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
    }

    public ArticlePage clickOnSearchButton() {
        searchButton.click();
        return new ArticlePage(driver);
    }

    public HomePage clickOnLogoImage() {
        logoImage.click();
        return new HomePage(driver);
    }

}