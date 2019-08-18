package tests.snow.api;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import cucumber.api.java.en.Given;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.qameta.allure.Step;
import lib.restassured.SNowRestAPI;

public class CreateIncident extends SNowRestAPI {
	
	/*
	 * This test creates an incident in Servicenow app. This test implements
	 * - Request and response builder
	 * - Query parameter or Body as file 
	 * - TestNG data provider
	 * - Allure Report tagging
	 */
	
	@BeforeTest
	public void setup() {
		fileName = "POST_Incident";
		startTest = 1;
		endTest = 2;
		paths	= new Object[] {"TestID","Body","QueryParam", "StatusCode","Status line"};

	}
	
	
	
	@Step
	@Test(dataProvider="JSONData")
	public void createAnIncident(String testID, Object body, ArrayList<String> QueryParam, Integer statusCode, String statusline) {		
		
		//Test 
		given().
				spec(setRequestSpec()).
				//queryParam("sysparm_fields", QueryParam).
				body(body).
		when().
				post("/incident").
		then().
				spec(setResponseSpec(statusCode)).
				statusLine(containsString(statusline)).
				log().all();
	}
	
	//Running from cucumber
	@Given("Create an Incident using API and verify (.*)")
	public void createAnIncidentUsingCucumber(Integer statuscode) {		
		
		//Test 
		given().
				spec(setRequestSpec()).
				//queryParam("sysparm_fields", QueryParam).
				body("").
		when().
				post("/incident").
		then().
				spec(setResponseSpec(statuscode)).
				statusLine(containsString("Created")).
				log().all();
	}

}
