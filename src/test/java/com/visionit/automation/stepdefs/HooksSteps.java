package com.visionit.automation.stepdefs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.visionit.automation.core.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;

public class HooksSteps {

	private static final Logger logger = LogManager.getLogger(HooksSteps.class);
	public static WebDriver driver;
	Scenario scn;

	@Before
	public void setUp(Scenario scn) throws Exception {
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");
	}

	@After(order = 1)
	public void cleanUp() {
		WebDriverFactory.quitDriver();
		scn.log("Browser Closed");
	}

	@After(order = 2)
	public void takeScreenShot(Scenario s) throws InterruptedException {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot) driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Failed Step Name: " + s.getName());
		} else {
			scn.log("Test case is passed, no screen shot captured");
		}
	}

	@BeforeStep
	public void beforeSteps() {
		// System.out.println("==========@BeforeStep\n");
	}

	@AfterStep
	public void afterSteps() {
		// System.out.println("==========@AfterStep\n");
	}
}
