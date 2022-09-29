package com.visionit.automation.stepdefs;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.visionit.automation.core.WebDriverFactory;
import com.visionit.automation.pageobjects.PricingPage;
import com.visionit.automation.pageobjects.TabPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class PricingSteps {

	WebDriver driver;
	PricingPage pricing_page;
	TabPage tabpage;
	JavascriptExecutor js;

	public PricingSteps() {
		driver = HooksSteps.driver;
		pricing_page = new PricingPage(driver);
		tabpage = new TabPage(driver);
	}

	@Given("I went to the Sleek SG Home page")
	public void i_went_to_the_sleek_sg_home_page() {
		driver.navigate().to("https://sleek.com/sg/");
	}

	@When("I click on the Pricing link")
	public void i_click_on_the_pricing_link() {
		pricing_page.clickOnPricingMenuButton();
	}

	@Then("I should be navigated to the Sleek SG Pricing page")
	public void i_should_be_navigated_to_the_sleek_sg_pricing_() throws InterruptedException {
		tabpage.checkPageIsDisplayed("https://sleek.com/sg/");
		WebDriverFactory.waitTime(10);
	}
	
	@Given("I am on the Sleek SG Pricing page")
	public void i_am_on_the_sleek_sg_pricing_page() {
		driver.navigate().to("https://sleek.com/sg/all-services/");
		WebDriverFactory.waitTime(10);
	}

	@When("I click on the Find out more button in Corporate secretary")
	public void i_click_on_the_find_out_more_button_in_corporate_secretary() {
		pricing_page.clickOnFindOutMoreButton();
	}
	@When("Select your Corporate Secretary plan")
	public void select_your_corporate_secretary_plan() {
		pricing_page.selectPlan();
		WebDriverFactory.waitTime(10);
	}
	@Then("Verify corporate secretary details are correct: {string} {string}")
	public void verify_corporate_secretary_details_are_correct(String string, String string2) {
		pricing_page.verifyAmount(string, string2);
		WebDriverFactory.waitTime(10);
	}
}
