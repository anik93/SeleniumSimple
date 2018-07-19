package com.anik.selenium.model.enums;

import java.io.File;
import java.util.logging.Level;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.anik.selenium.model.enums.interfaces.DriverSetup;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import io.github.bonigarcia.wdm.OperaDriverManager;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;

public enum WebDriverType implements DriverSetup {
	FIREFOX {
		@Override
		public FirefoxDriver create() {
			FirefoxDriverManager.getInstance().setup();
			/*proxyServer.start();
			proxyServer.setHarCaptureTypes(CaptureType.getAllContentCaptureTypes());
			proxyServer.enableHarCaptureTypes(CaptureType.REQUEST_CONTENT, CaptureType.RESPONSE_CONTENT);
			proxyServer.newHar(LocalDateTime.now().toString());

			Proxy proxy = ClientUtil.createSeleniumProxy(proxyServer);
			FirefoxProfile profile = new FirefoxProfile();
			String host = proxy.getHttpProxy().split(":")[0];
			int port = Integer.parseInt(proxy.getHttpProxy().split(":")[1]);
			profile.setPreference("network.proxy.type", 1);
			profile.setPreference("network.proxy.http", host);
			profile.setPreference("network.proxy.http_port", port);
			profile.setPreference("network.proxy.ssl", host);
			profile.setPreference("network.proxy.ssl_port", port);
			profile.setPreference("acceptInsecureCerts", true);
*/
			//DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
			//desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, this.createBrowserLog());
			//desiredCapabilities.setAcceptInsecureCerts(true);
			//desiredCapabilities.setCapability(FirefoxDriver.PROFILE, profile);
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			return new FirefoxDriver(firefoxBinary);//desiredCapabilities);
		}

	},
	CHROME {
		@Override
		public ChromeDriver create() {
			ChromeDriverManager.getInstance().setup();
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("--start-maximized");
			chromeOptions.addArguments("headless");
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, this.createBrowserLog());
			desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
			return new ChromeDriver(desiredCapabilities);
		}
	},
	IE {
		@Override
		public InternetExplorerDriver create() {
			InternetExplorerDriverManager.getInstance().arch32().setup();
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.internetExplorer();
			desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
					true);
			// desiredCapabilities.setCapability("initialBrowserUrl", url);
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, this.createBrowserLog());
			return new InternetExplorerDriver(desiredCapabilities);
		}
	},
	OPERA {
		@Override
		public OperaDriver create() {
			OperaDriverManager.getInstance().setup();
			OperaOptions operaOptions = new OperaOptions();
			operaOptions.setBinary(new File(System.getProperty("opera")));
			DesiredCapabilities capabilities = DesiredCapabilities.operaBlink();
			capabilities.setCapability("opera.no_quit", false);
			capabilities.setCapability(OperaOptions.CAPABILITY, operaOptions);
			return new OperaDriver(capabilities);
		}
	},
	SAFARI {
		@Override
		public SafariDriver create() {
			return new SafariDriver();
		}
	},
	ANDROID {
		@Override
		public RemoteWebDriver create() {
			AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
					new AppiumServiceBuilder().usingDriverExecutable(new File(System.getProperty("nodeJS")))
							.withAppiumJS(new File(System.getProperty("appiumJS"))).withIPAddress("127.0.0.1")
							.usingAnyFreePort());
			service.start();
			if (!service.isRunning())
				throw new AppiumServerHasNotBeenStartedLocallyException("An appium server node is not started!");
			DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
			desiredCapabilities.setCapability("deviceName", "Android Emulator");
			// desiredCapabilities.setCapability("platformVersion", "8.0.0");
			desiredCapabilities.setCapability("platformVersion", "4.3.0");
			desiredCapabilities.setCapability("platformName", "Android");
			desiredCapabilities.setCapability("browserName", "Browser");
			return new RemoteWebDriver(service.getUrl(), desiredCapabilities);
		}
	},
	BLACKBERRY {
		@Override
		public ChromeDriver create() {
			return new ChromeDriver();
		}
	},
	IOS {
		@Override
		public ChromeDriver create() {
			return new ChromeDriver();
		}
	},
	EDGE {
		@Override
		public EdgeDriver create() {
			return new EdgeDriver();
		}
	},
	PHANTOMJS {
		@Override
		public PhantomJSDriver create() {
			PhantomJsDriverManager.getInstance().setup();
			LoggingPreferences loggingPreferences = new LoggingPreferences();
			loggingPreferences.enable(LogType.BROWSER, Level.ALL);
			DesiredCapabilities desiredCapabilities = DesiredCapabilities.phantomjs();
			desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, this.createBrowserLog());
			return new PhantomJSDriver(desiredCapabilities);
		}
	};
}
