package util;

import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {
	
	/*
	 * This method is used to write data as a JSON file
	 * @param: data in string format
	 * @author: QualityArc
	 */

	public static void writeJSONdata(String filename, String data) {
		FileWriter writer = null;
		try {
			writer = new FileWriter("./data/"+filename+".json");
			writer.write(data);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
