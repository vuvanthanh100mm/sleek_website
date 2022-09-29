package com.visionit.automation.pageobjects;
import static org.junit.Assert.assertThat;
import org.hamcrest.CoreMatchers;
import org.openqa.selenium.WebDriver;
import com.visionit.automation.core.WebDriverFactory;

public class TabPage {

	WebDriver driver;

	public TabPage(WebDriver driver) {
		this.driver = driver;
	}

	public void checkPageIsDisplayed(String pageLink) {
		WebDriverFactory.switchBrowserToTab();
		String strUrl = driver.getCurrentUrl();
		assertThat(strUrl, CoreMatchers.containsString(pageLink));
	}
}
