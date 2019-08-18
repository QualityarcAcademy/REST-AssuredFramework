package runner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"src/test/resources/features"},
				glue = {"stepdef","tests.snow.api"},
				plugin = {},
				monochrome = true
				
					)


public class CucumberRunner {
	


}
