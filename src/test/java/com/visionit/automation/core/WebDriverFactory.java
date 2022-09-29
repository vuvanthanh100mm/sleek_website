package com.visionit.automation.core;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	private static WebDriver driver = null;

	public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			logger.info("Chrome Browser invoked");
			break;
		case "firefox":
			driver = new FirefoxDriver();
			logger.info("Firefox Browser invoked");
			break;
		case "headless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
			logger.info("Headless Chrome Browser invoked");
			break;
		default:
			logger.fatal("No such browser is implemented.Browser name sent: " + browser);
			throw new Exception("No such browser is implemented.Browser name sent: " + browser);
		}
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		logger.info("Driver maximized and implicit time out set to 20 seconds");
		return driver;
	}

	public static void navigateToTheUrl(String url) {
		driver.get(url);
		logger.info("Browser navigated to the url: " + url);
	}

	public static void quitDriver() {
		driver.quit();
		logger.info("Driver closed");
	}

	public static void waitTime(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {

		}
		logger.info("waitTime");
	}

	public static void switchBrowserToTab() {
		String parent = driver.getWindowHandle();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> I1 = s.iterator();
		while (I1.hasNext()) {
			String child_window = I1.next();
			if (!parent.equals(child_window)) {
				driver.switchTo().window(child_window);
				break;
			}
		}
		logger.info("Switched to the new window/tab");
	}

	public static void switchToOriginalTab() {
		Set<String> handles = driver.getWindowHandles(); // get all the open windows
		logger.info("List of windows found: " + handles.size());
		logger.info("Windows handles: " + handles.toString());
		Iterator<String> it = handles.iterator(); // get the iterator to iterate the elements in set
		String original = it.next();// gives the parent window id
		// String nextTab = it.next();//gives the child window id
		driver.switchTo().window(original);
		logger.info("Switched to the original window/tab");

	}

	public static String getBrowserName() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver2.exe");
		String browserDefault = "chrome";
		String browserSentFromCmd = System.getProperty("browser");
		if (browserSentFromCmd == null) {
			return browserDefault;
		} else {
			return browserSentFromCmd;
		}
	}

	public static void ScrollElementIntoView(By xpath) {
		WebElement element = driver.findElement(xpath);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
