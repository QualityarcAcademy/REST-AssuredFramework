package util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;



public class JSONReader {

	public static void readJSONdata(String filename, String pathToRead) {
		JSONParser jsonParser = new JSONParser();
		FileReader reader = null;
		try {
			reader = new FileReader("./data/"+filename+".json");
			Object obj = jsonParser.parse(reader);
			JSONArray arrayList = (JSONArray)obj;
			System.out.println(arrayList.get(0));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	//TEST
	public static void main(String[] args) {
		readJSONdata("output","data");
	}

}
