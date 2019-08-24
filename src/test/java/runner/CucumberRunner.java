package runner;
import cucumber.api.CucumberOptions;

@CucumberOptions(features = {"src/test/resources/features"},
				glue = {"stepdef","tests.snow.api"},
				plugin = {},
				monochrome = true
				
					)


public class CucumberRunner {
	

	


}
