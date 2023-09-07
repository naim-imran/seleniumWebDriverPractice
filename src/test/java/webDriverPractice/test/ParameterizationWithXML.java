package webDriverPractice.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParameterizationWithXML {
	@Parameters({"url", "user"})
	@Test()
	public void getURL(String url, String user) {
		System.out.println("URL= "+url + " user= "+ user);
	}
}
