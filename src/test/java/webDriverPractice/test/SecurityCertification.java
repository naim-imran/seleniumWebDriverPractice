package webDriverPractice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v104.security.Security;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class SecurityCertification {
	WebDriver driver;
	DevTools devTools;

	@BeforeMethod
	public void initialize() throws InterruptedException {

		InitialComponents startBrowsernew = new InitialComponents();
		driver = startBrowsernew.launchBrowser();

		devTools = ((ChromeDriver) driver).getDevTools();
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
		
		Thread.sleep(2000);

	}

	@Test
	public void desiredCapabilitiesTest() throws InterruptedException {
		devTools.send(Security.enable());
		devTools.send(Security.setIgnoreCertificateErrors(true));
		
		driver.get("https://assured-id-root-ca-expired.chain-demos.digicert.com/");
		Thread.sleep(2000);
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
