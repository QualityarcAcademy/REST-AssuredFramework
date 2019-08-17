package sandbox;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.http.ContentType;

public class postCreateInc {

	@Test
	public void createAnIncident() throws IOException {

		//Serialize Java object to JSON

		//		SerializationJSON jsonBody = new SerializationJSON();
		//		jsonBody.setCaller_id("Abel Tuter");
		//		jsonBody.setDescription("Data created by Serialization");

		//Using Map

		//		Map<String, Object>  jsonAsMap = new HashMap<>();
		//		jsonAsMap.put("caller_id", "Abel Tuter");
		//		jsonAsMap.put("description", "Using Map as json");

		//Using json file 
		ObjectMapper jsonData = new ObjectMapper();
		File file = new File("./data/incidentdata.json");
		List<Map<String, Object>> mapObject=  jsonData.readValue(file, new TypeReference<List<Map<String, Object>>>(){});
		//jsonData.readTree(file).path("");
		System.out.println("The map value is "+mapObject);
		System.out.println("The size is "+mapObject.size());

		//Convert list of map to two dimentional array 

		Object[][] arrayData = new Object[mapObject.size()][1];


		for (int i=0; i<2; i++) {
			for (int j=0; j<1; j++) {
				arrayData[i][j]= mapObject.get(i);
			}

		}

		System.out.println(arrayData[1][0]);


		//Create an incident	
				given().
						auth().basic("admin", "ErMiWa0E0mlP").
						request().body(arrayData[0][0]).
						contentType(ContentType.JSON).
						log().body().
				when().
						post("https://dev84192.service-now.com/api/now/table/incident").
						
				then().
						assertThat().statusCode(201).
						log().body(true);

	}

}
