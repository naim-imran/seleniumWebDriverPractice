package webDriverPractice.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class R0021_parameterizationWithXML {
	@Parameters({"url", "user"})
	@Test()
	public void getURL(String a, String b) {
		System.out.println(a + " " + b);
	}
}
