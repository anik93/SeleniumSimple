package com.selenium.test.feature;

import com.selenium.test.page.ArticlePage;
import com.selenium.test.page.HomePage;
import com.selenium.test.pop.FunctionalTest;
import cucumber.api.java8.En;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class WikipediaFeature implements En {
    private FunctionalTest functionalTest;
    private HomePage homePage;
    private ArticlePage articlePage;

    public WikipediaFeature(FunctionalTest fn) {
        functionalTest = fn;

        When("^I click on search button$", () -> articlePage = functionalTest.getHomePage().clickOnSearchButton());

        Then("^I should see \"([^\"]*)\" article$", (String text) ->
                assertThat(Optional.ofNullable(functionalTest.getArticlePage()).map(article -> article).orElse(articlePage).checkTitleOfArticle(text)).isTrue());

        And("^I set \"([^\"]*)\" into search input$", (String text) -> functionalTest.getHomePage().setTextIntoSearchInput(text));

        And("^I open main page$", () -> articlePage.clickOnLogoImage());
    }
}
