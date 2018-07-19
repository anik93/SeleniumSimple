package com.anik.selenium.model.enums.interfaces;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import cucumber.api.Scenario;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;

public interface DriverSetup {

    public final BrowserMobProxyServer proxyServer = new BrowserMobProxyServer();

    public default void getHar(String scenarioName) {
        Har har = proxyServer.getHar();
        har.getLog().getEntries().removeIf(x -> !x.getRequest().getMethod().equals("POST")
                || !x.getRequest().getUrl().startsWith(System.getProperty("url")));
        try {
            new File("target/hars").mkdir();
            har.writeTo(new File("target/hars/" + scenarioName + " "
                    + LocalDateTime.now().toString().replaceAll(":", "_") + ".har"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public default void readConsole(WebDriver driver, Scenario scenario) {
        driver.manage().logs().get(LogType.BROWSER).filter(Level.SEVERE)
                .forEach(log -> scenario.write(log.getTimestamp() + " " + log.getMessage()));
    }

    public default LoggingPreferences createBrowserLog() {
        LoggingPreferences loggingPreferences = new LoggingPreferences();
        loggingPreferences.enable(LogType.BROWSER, Level.ALL);
        return loggingPreferences;
    }

    public WebDriver create();

}
