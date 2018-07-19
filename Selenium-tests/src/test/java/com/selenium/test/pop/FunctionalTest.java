package com.selenium.test.pop;

import java.util.concurrent.TimeUnit;

import com.selenium.test.page.ArticlePage;
import com.selenium.test.page.HomePage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;

import com.anik.selenium.model.enums.WebDriverType;

import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class FunctionalTest implements En {

    private WebDriver driver;
    private Scenario scenario;
    //private String url = System.getProperty("url");
    private WebDriverType driverType;
    private HomePage homePage;
    private ArticlePage articlePage;

    public FunctionalTest() {
        String[] adnotation = {"@scenarioTest"};
        Before(adnotation, (Scenario scenario) -> {
            this.scenario = scenario;
        });

        After(adnotation, () -> {
            if (this.scenario.isFailed())
                this.scenario.embed(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES), "image/png");

            if (this.driver.getClass().equals(OperaDriver.class)) {
                this.driver.close();
                this.driver.quit();
            } else if (!this.driver.getClass().equals(InternetExplorerDriver.class))
                this.driver.close();
            else
                this.driver.quit();
        });

        Given("^I navigate to \"([^\"]*)\" using \"([^\"]*)\"$", (String path, WebDriverType webDriverType) -> {
            initWebDriver(webDriverType, path);
            homePage = new HomePage(driver);
        });

        Given("^I navigate to article \"([^\"]*)\" using \"([^\"]*)\"$", (String path, WebDriverType webDriverType) -> {
            initWebDriver(webDriverType, path);
            articlePage = new ArticlePage(driver);
        });
    }

    private void initWebDriver(WebDriverType webDriverType, String path) {
        this.driver = webDriverType.create();
        this.driver.manage().deleteAllCookies();
        if (!this.driver.getClass().equals(InternetExplorerDriver.class)) {
            this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            this.driver.get(path);
        } else
            this.driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public HomePage getHomePage() {
        return homePage;
    }

    public void setHomePage(HomePage homePage) {
        this.homePage = homePage;
    }

    public ArticlePage getArticlePage() {
        return articlePage;
    }

    public void setArticlePage(ArticlePage articlePage) {
        this.articlePage = articlePage;
    }
}
