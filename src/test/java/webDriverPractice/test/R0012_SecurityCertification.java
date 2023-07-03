package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v104.security.Security;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0012_SecurityCertification {
	WebDriver driver;
	DevTools devTools;

	@BeforeMethod
	public void initialize() throws InterruptedException {

		InitialComponents startBrowsernew = new InitialComponents();
		driver = startBrowsernew.launchBrowser();

		devTools = ((ChromeDriver) driver).getDevTools();
		

		Thread.sleep(2000);

	}

	@Test(description = "validate the SSL certification using chrome DecTool")
	public void devToolsTest() throws InterruptedException {
		//access browser before enabling security. 
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
		Thread.sleep(5000);
		System.out.println(driver.getTitle()+ " " + new Date().getTime());
		devTools.send(Security.enable());
		devTools.send(Security.setIgnoreCertificateErrors(true));
		
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
