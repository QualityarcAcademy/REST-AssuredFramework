package lib.restassured;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import groovy.json.JsonSlurper;

public class ReusableLib {


	//Request re-usable methods

	

	/*
	 * Major operations
	 * extract
	 * store
	 * verify - groovy
	 */

	//Response re-usable methods

	/*
	 * Extract single value from response
	 */

	public String extractSingleVal(ValidatableResponse response, String path) {
		String val = response.extract().path(path);
		return val;

	}
	/*
	 * Extract multiple values from response as a list
	 */

	public List<String> extractMultipleValAsList(ValidatableResponse response, String path) {
		ArrayList<String> val = response.extract().path(path);
		return val;

	}

	/*
	 * Extract multiple values from response as a map
	 */

	public List<Map<String, ?>> extractMultipleValAsMap(ValidatableResponse response, String path) {
		ArrayList<Map<String, ?>> val = response.extract().path(path);
		return val;

	}

	/*
	 * Store List as JSON value
	 */

	public void extractAndStoreListAsJson(ValidatableResponse response, String path) {
		ArrayList<String> val = response.extract().path(path);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			mapper.writeValue(new File("./data/output.json"), val);
		}  catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Store Map value as JSON 
	 */

	public void extractAndStoreMapAsJson(ValidatableResponse response, String path) {
		ArrayList<Map<String, ?>> val = response.extract().path(path);
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			mapper.writeValue(new File("./data/output.json"), val);
		}  catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * Store as JSON value
	 */

	public void extractFullResponseAndStoreAsJson(ValidatableResponse response) {
		String responseBody = response.extract().response().jsonPath().prettify();
		ObjectMapper mapper = new ObjectMapper(); 
		FileWriter write = null;
		try {
			write = new FileWriter(new File("./data/output.json"));
			write.write(responseBody);
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//EOF
}
