package webDriverPractice.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PropertiesClass {

	Properties prop;

	@BeforeMethod
	public void setup() {
		try {
			prop = new Properties();
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/webDriverPractice/initialization/config.properties");
			prop.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void propertiesFileTest() {

		System.out.println(prop.getProperty("userName"));
		System.out.println(prop.getProperty("password"));

	}

}
