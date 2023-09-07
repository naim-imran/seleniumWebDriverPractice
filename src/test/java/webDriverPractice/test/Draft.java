package webDriverPractice.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;




public class Draft {

	public static void main(String[] args) throws MalformedURLException, IOException {

	
	  ChromeOptions options = new ChromeOptions();
	 options.setBrowserVersion("115");
	  
	  ChromeDriver driver = new ChromeDriver(options);
		
		  DevTools chromedevTool = driver.getDevTools(); chromedevTool.createSession();
		  
		  
		  
		  Map<String, Object> coordinates = new HashMap<String, Object>();
		  coordinates.put("latitude", 19.43); coordinates.put("longitude", -99.13);
		  coordinates.put("accuracy", 50);
		  
		  driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
		 
	  driver.get("https://google.com");
		
		  driver.findElement(By.xpath("//textarea[@id='APjFqb']")).sendKeys("netflix",
		  Keys.ENTER); driver.navigate().refresh();
		  driver.findElement(By.xpath("//span[text()='netflix.com']")).click();
		 driver.quit();
	  //driver.findElement(By.xpath("//div[@class='FE52Uc sjVJQd']")).click();
	 	

   
	
	
	}

}
//  "https://www.ebayinc.com/company/"