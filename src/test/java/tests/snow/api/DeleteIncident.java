package tests.snow.api;

import static io.restassured.RestAssured.given;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.ValidatableResponse;
import lib.restassured.SNowRestAPI;

public class DeleteIncident extends SNowRestAPI {
	
	/*
	 * This test deletes an existing incident based on sys_id in Servicenow app. This test implements
	 * - Request and response builder
	 * - Path parameter
	 * - Body as file 
	 * - TestNG data provider
	 * - Extracts a single value from response and chains the requests 
	 * - Allure Report tagging
	 */
	
	@BeforeTest
	public void setup() {
		fileName = "Delete_Incident";
		startTest = 1;
		endTest = 2;
		paths	= new Object[] {"TestID","Body", "StatusCode","Status line"};

	}
	@Test(dataProvider="JSONData")
	public void putRequest(String testID, Object body, Integer statusCode, String statusline) {
		ValidatableResponse response =
				given().
						spec(setRequestSpec()).body(body).
				when().
						post("/incident").
				then().
						spec(setResponseSpec(201));

				//Get the sys_id from the response
				String sysID = extractSingleVal(response, "result.sys_id");
				
				
				//Modify an existing resource
				given().
						spec(setRequestSpec()).
						body(body).
				when().
						delete("/incident/"+sysID).
				then().
						spec(setResponseSpec(statusCode)).
						log().all();
				
	}

}
