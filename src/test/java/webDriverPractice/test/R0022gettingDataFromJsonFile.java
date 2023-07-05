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

public class R0022gettingDataFromJsonFile {
	
		public List<HashedMap<String, String>> jsonReader(String filePathFromSRC) throws IOException {
		String jsonContents = FileUtils.readFileToString(new File(System.getProperty("user.dir")+filePathFromSRC), StandardCharsets.UTF_8);
		ObjectMapper mapper = new ObjectMapper();
		List<HashedMap<String, String>> data = mapper.readValue(jsonContents,new TypeReference<List<HashedMap<String, String>>>(){
		});	
		return data;		
	}
		
		@Test
		public void printJsonData() throws IOException {
			List<HashedMap<String, String>> jsonData = jsonReader("/src/main/java/webDriverPractice/initialization/loginData.json");	
			jsonData.forEach(s->System.out.println(s.get("email") + " = " + s.get("password")));
		}
		
		
		
		
		
		
}
