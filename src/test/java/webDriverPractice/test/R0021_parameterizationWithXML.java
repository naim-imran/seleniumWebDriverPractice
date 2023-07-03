package webDriverPractice.test;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class R0021_parameterizationWithXML {
	@Parameters({"url"})
	@Test()
	public void getURL(String a) {
		System.out.println(a);
	}
}
