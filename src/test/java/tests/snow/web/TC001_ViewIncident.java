package tests.snow.web;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import lib.selenium.SNowWeb;
import pages.snow.LoginPage;

public class TC001_ViewIncident extends SNowWeb {

	@BeforeTest
	public void setup() {
		browserName = Browser.CHROME;
		dataSheetName = "Sheet1";

	}

	@Test(dataProvider="ExcelData")
	public void viewIncident(String username, String password, String searchWord) {
		new LoginPage(driver).
		enterUsername(username).
		enterPassword(password).
		clickLoginBtn().
		searchIncident(searchWord).
		clickOnOpen().
		switchToFrame().
		searchIncidentNumber("INC0010125");
		
	}

}
