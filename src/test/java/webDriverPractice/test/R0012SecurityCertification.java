package webDriverPractice.test;

import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0012SecurityCertification extends ThreadSafeDriver{
	private WebDriver driver;
	

	@BeforeMethod
	public void initialize() throws InterruptedException {

		
		driver = launchBrowser();

		//devTools = ((ChromeDriver) driver).getDevTools();
		

		Thread.sleep(2000);

	}

	@Test(description = "validate the SSL certification using chrome DecTool")
	public void devToolsTest() throws InterruptedException {
		//access browser before enabling security. 
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
		Thread.sleep(5000);
		System.out.println(driver.getTitle()+ " " + new Date().getTime());
		//devTools.send(org.openqa.selenium.devtools.v114.security.Security.enable());
		//devTools.send(org.openqa.selenium.devtools.v114.security.Security.setIgnoreCertificateErrors(true));
		
		//accessing browser after enabling security.
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
		System.out.println(driver.getTitle());
		System.out.println(driver.getCurrentUrl()+ " " + new Date().getTime());
		
		Thread.sleep(2000);
	}
	
	@Test(description = "validate the SSL certification using chromeOptions class")
	public void securityCertificates() {
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
	}

	@AfterMethod
	public void quitBrowser() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}

}
