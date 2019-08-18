package stepdef;

import cucumber.api.java.en.Given;
import lib.selenium.SNowWeb;
import pages.snow.HomePage;
import pages.snow.LoginPage;

public class SnowWebSteps extends SNowWeb {


	@Given("Login servicenow using given (.*) and (.*)")
	public void logintoServicenow(String username, String password) {
		OpenBrowser(Browser.CHROME);
		new LoginPage(driver).
		enterUsername(username).
		enterPassword(password).
		clickLoginBtn();

	}
	
	@Given("Search the incident using (.*) and enter (.*)")
	public void verifyIncident(String searchWord, String incidentNum) {
		new HomePage(driver).
		searchIncident(searchWord).
		clickOnOpen().
		switchToFrame().
		searchIncidentNumber(incidentNum);

	}
	



}
