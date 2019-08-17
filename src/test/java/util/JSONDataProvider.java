package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

public class JSONDataProvider {

	/*
	 * This data provider enables to read particular set of data for specific tests. This can be useful for different 
	 * set of tests incl sanity, functional, regression. 
	 * 
	 * This method deserializes the json object and stores specific java object in two dimensional array 
	 * @param: fileName, startTest,endTest, pathsToRead
	 * @author: QualityArc
	 */

	public static Object[][] readJsonData(String fileName, int startTest, int endTest, Object[] pathsToRead){

		Object[][] data = null;

		//Read JSON and get its node
		ObjectMapper mapper = new ObjectMapper();
		File file = new File("./data/"+fileName+".json");

		//Store it in two dimensional array 
		data = new Object[(endTest-startTest)+1][pathsToRead.length]; //two rows and two columns 

		int k=0;
		for (int i=(startTest-1); i<endTest; i++) {
			for(int j=0; j<pathsToRead.length; j++){
				JsonNode node = null;
				try {
					node = mapper.readTree(file).at("/"+i+"/"+pathsToRead[j]);//Using jsonpointer
					if(node.getNodeType().equals(JsonNodeType.STRING)) {
						data[k][j] = mapper.treeToValue(node, String.class);
					}else if (node.getNodeType().equals(JsonNodeType.NUMBER)) {
						data[k][j] = mapper.treeToValue(node, Integer.class);
					}else if(node.getNodeType().equals(JsonNodeType.ARRAY)) {
						data[k][j] = mapper.treeToValue(node, ArrayList.class);
					}else if(node.getNodeType().equals(JsonNodeType.BOOLEAN)) {
						data[k][j] = mapper.treeToValue(node, Boolean.class);
					}else {
						data[k][j] = node;//node old value
					}
				}

				catch (IOException e) {
					e.printStackTrace();
				} 
			}
			k++;
		}
		//System.out.println(Arrays.deepToString(data));
		return data;

	}

	//Test Method

	//	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
	//		String[] paths = {"TestID","Body","StatusCode","Status line"};
	//		readJsonData("test",2,paths);
	//	}

}
