package webDriverPractice.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.openqa.selenium.devtools.v114.network.Network;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class R0024ChromeDevTool {
	ChromeDriver chromeDriver;
	private DevTools chromeDevTool;

	@BeforeMethod
	public void initializeDependencies() {

		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		chromeDriver = new ChromeDriver(co);

		// ChromeDriver driver = (ChromeDriver) R0019
		chromeDevTool = chromeDriver.getDevTools();
		chromeDevTool.createSession();
	}

	@AfterMethod
	public void closeBrowserInstance() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// chromeDriver.close();
	}

	@Test
	public void openBrowserInMobileView() {
		chromeDevTool.send(Emulation.setDeviceMetricsOverride(600, 1000, 100, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty()));
		chromeDriver.get("https://www.amazon.com/");
		chromeDriver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Laptops");
	}

	@Test
	public void testGeoLocation() throws InterruptedException {
		chromeDevTool.send(Emulation.setDeviceMetricsOverride(600, 1000, 100, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty()));

		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude", 40);
		coordinates.put("longitude", 3);
		coordinates.put("accuracy", 1);

		chromeDriver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		chromeDriver.get("https://www.google.com/");
		chromeDriver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("netflix", Keys.ENTER);
		chromeDriver.navigate().refresh();
		chromeDriver.findElement(By.xpath("//span[text()='netflix.com']")).click();

		// chromeDriver.findElement(By.xpath("//h3[contains(text(),'Netflix - Watch TV
		// Shows Online, Watch Movies Onli')]")).click();
	}

	@Test
	public void testNetwork() {
		chromeDevTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		chromeDevTool.addListener(Network.responseReceived(), response -> {
			System.out.print("Response ID " + response.getRequestId() + " = ");
			System.out.println(response.getResponse().getStatus());
		});

		
		  chromeDriver.get("https://www.google.com/");
			/*
			 * chromeDriver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys(
			 * "netflix", Keys.ENTER); chromeDriver.navigate().refresh();
			 * chromeDriver.findElement(By.xpath("//span[text()='netflix.com']")).click();
			 */
		 
	}
}
























