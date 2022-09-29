package com.visionit.automation.pageobjects;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.visionit.automation.core.WebDriverFactory;

public class PricingPage {

	WebDriver driver;
	private By pricing_menu = By.xpath("//*[contains(text(),'Pricing')]");
	private By corporate_secretary = By.xpath("//*[contains(text(),'Now everyone can register a business!')]");
	private By find_more_out = By.xpath("(//a[@id=\'home-lets-talk\']/span[2]/span)[7]");
	private By secretary_plan = By.xpath("//*[contains(text(),'Fixed fee. Unlimited service!')]");
	private By shareholders = By.id("no_of_shareholders");
	private By txt_amount = By.id("text_new_amount");

	public PricingPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnPricingMenuButton() {
		driver.findElement(pricing_menu).click();
	}

	public void clickOnFindOutMoreButton() {
		WebDriverFactory.ScrollElementIntoView(corporate_secretary);
		WebElement element = driver.findElement(find_more_out);
		Actions actions = new Actions(driver);
		actions.moveToElement(element).click().build().perform();
	}

	public void selectPlan() {
		WebDriverFactory.ScrollElementIntoView(secretary_plan);
	}

	public void verifyAmount(String plan, String amount) {
		driver.findElement(shareholders).click();
		WebElement dropdown = driver.findElement(shareholders);
		dropdown.findElement(By.xpath("//option[. = '" + plan + "']")).click();
		String text = driver.findElement(txt_amount).getText();
		assertEquals(text, amount);
	}
}
