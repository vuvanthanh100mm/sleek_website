package com.visionit.automation.runners;
import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = { "com.visionit.automation.stepdefs" }, monochrome = true, plugin = {
		"json:target/cucumber.json" })
public class TestRunner {

}
