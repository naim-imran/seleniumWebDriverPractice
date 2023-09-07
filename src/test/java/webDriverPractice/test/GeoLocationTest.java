package webDriverPractice.test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.emulation.Emulation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoLocationTest {
	@Test
	public void testAppInDifferntGioLocation() {
		ChromeOptions options = new ChromeOptions();
		options.setBrowserVersion("116");

		ChromeDriver driver = new ChromeDriver(options);
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		DevTools chromedevTool = driver.getDevTools();
		chromedevTool.createSession();
		
	
		
		Map<String, Object> coordinates = new HashMap<String, Object>();
		coordinates.put("latitude", 19.43);
		coordinates.put("longitude", -99.13);
		coordinates.put("accuracy", 50);

		driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		
		chromedevTool.send(Emulation.setDeviceMetricsOverride(400, 641, 100, true, Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty(), Optional.empty()));

		driver.get("https://mx.ebay.com/");
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "Artículos electrónicos, autos, ropa, objetos de colección, cupones y más | eBay");
		//driver.quit();
		
		
		
		/*
		 * driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("netflix",
		 * Keys.ENTER); driver.navigate().refresh();
		 * driver.findElement(By.xpath("//span[text()='netflix.com']")).click();
		 * driver.quit();
		 */
	}
}
