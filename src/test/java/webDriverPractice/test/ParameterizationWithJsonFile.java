package webDriverPractice.test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ParameterizationWithJsonFile {
	
		public List<HashedMap<String, String>> jsonReader(String filePathFromSRC) throws IOException {
			// first we have to get the json file converted into String object by using readfileToString() method present in FileUtils class.
		String jsonContents = FileUtils.readFileToString(new File(System.getProperty("user.dir")+ filePathFromSRC ), StandardCharsets.UTF_8);
			
			// now we have the json file as String object. but we have to convert it as list of objects.
			// To achieve that we can use readvalue() method present in ObjectMapper  class.
		ObjectMapper objectMapper = new ObjectMapper();
		List<HashedMap<String, String>> data = objectMapper.readValue(jsonContents,new TypeReference<List<HashedMap<String, String>>>(){
		});	
		return data;		
	}
			// now we can read data by utilizing the forEach loop and get() method.
		@Test
		public void printJsonData() throws IOException {
			List<HashedMap<String, String>> jsonData = jsonReader("\\src\\main\\java\\webDriverPractice\\utilities\\loginData.json");	
			jsonData.forEach(s->System.out.println(s.get("email") + " = " + s.get("password")));
		}
		
		
		
		
		
		
}
