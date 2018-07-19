package com.selenium.tests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, plugin = {"pretty",
        "html:target/cucumberHtmlReport"}, features = "classpath:features", format = {
        "json:target/reportCucumber.json"}, glue = "com.selenium.test.feature"
//,dryRun = true
)
public class RunTest {

}
