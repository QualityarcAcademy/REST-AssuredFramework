package lib.restassured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.qameta.allure.Step;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import util.JSONDataProvider;

public class SNowRestAPI extends ReusableLib {
	public String URL, username, password, server;
	public long responseTime =10000;
	public RequestSpecification requestSpec;
	public ResponseSpecification responseSpec;
	public String fileName;
	public int startTest, endTest;
	public Object [] paths;

	public SNowRestAPI() {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./data/config.properties"));
			URL = prop.getProperty("URL");
			username = prop.getProperty("Username");
			password = prop.getProperty("Password");
			server = prop.getProperty("Server");
		}catch (IOException e) {
			e.printStackTrace();
		}

		//Set-up request spec


		//Set-up response spec




	}

	/*
	 * The request spec builder currently sets:
	 * - Basic Auth
	 * - Base URI
	 * - ContentType as JSON
	 * - Accepts response in JSON
	 * - Prints the log
	 */

	@Step("The REST API request specification")
	public RequestSpecification setRequestSpec() {
		BasicAuthScheme basicAuth = new BasicAuthScheme();
		basicAuth.setUserName(username);
		basicAuth.setPassword(password);

		//Set request builder
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.setBaseUri(URL).setAuth(basicAuth).setContentType(ContentType.JSON).setAccept(ContentType.JSON);
		requestSpec= reqBuilder.build();
		requestSpec.log().all();
		return requestSpec;

	}

	/*
	 * The request spec builder currently sets:
	 * - Confirms ContentType is JSON
	 * - Response time is no more than 5000 ms
	 * - The server name is ServiceNow
	 * - The response body is not null
	 * - Prints the whole response
	 */

	@Step("The REST API response specification")
	public ResponseSpecification setResponseSpec(int statusCode) {
		ResponseSpecBuilder builder = new ResponseSpecBuilder();
		//builder.expectContentType(ContentType.JSON); --Not applicable for DELETE 
		builder.expectStatusCode(statusCode);
		builder.expectResponseTime(lessThan(responseTime));
		builder.expectHeader("Server", server);
		builder.expectBody(notNullValue());
		responseSpec = builder.build();
		return responseSpec;

	}

	//String body = "{\"priority\":\"10\",\"impact\":\"10\",\"short_description\":\"API demo1\"}";

	/*
	 * TestNG - Data provider reads data from external file (JSON or excel)
	 * @param : 
	 * @return:Two dimensional array of objects 
	 * @author: QualityArc
	 * 
	 */
	
	@DataProvider(name="JSONData")
	public Object[][] jsondata(){
		return JSONDataProvider.readJsonData(fileName,startTest,endTest,paths);
	}



	//EOF

}
