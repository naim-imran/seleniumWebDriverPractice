package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0013_PropertiesClass {

	Properties prop;

	@BeforeMethod
	public void setup() {
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/webDriverPractice/utilities/config.properties");
			prop = new Properties();
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void propertiesFileTest() {
		System.out.println(prop.getProperty("userName"));
		System.out.println(prop.getProperty("password"));
	}

}
