package tests.snow.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import lib.restassured.SNowRestAPI;

public class RetrieveIncident extends SNowRestAPI {
	
	/*
	 * This test retrieves a specific incident based on sys_id in Servicenow app. This test implements
	 * - Request and response builder
	 * - Path parameter
	 * - Body as file 
	 * - TestNG data provider
	 * - Extracts a single value from response and chains the requests 
	 * - Allure Report tagging
	 */
	
	@BeforeTest
	public void setup() {
		fileName = "GET_Incident";
		startTest = 1;
		endTest = 2;
		paths	= new Object[] {"TestID","Body", "StatusCode","Status line"};

	}
	
	@Step
	@Test(dataProvider="JSONData")
	public void retrieveAnIncident(String testID, Object body, Integer statusCode, String statusline) {
		ValidatableResponse response =
				given().
						spec(setRequestSpec()).body(body).
				when().
						post("/incident").
				then().
						spec(setResponseSpec(201));
				
				//Get the sys_id from the response
				String sysID = extractSingleVal(response, "result.sys_id");
				
				//Get the incident		
				given().
						spec(setRequestSpec()).param("sys_id", sysID).
				when().
						get("/incident").
				then().
						spec(setResponseSpec(200)).
						statusLine(containsString(statusline)).
						log().all();

	}

}
