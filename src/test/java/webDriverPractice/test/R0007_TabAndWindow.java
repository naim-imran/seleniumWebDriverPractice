package webDriverPractice.test;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webDriverPractice.initialization.InitialComponents;

public class R0007_TabAndWindow {
	WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() throws InterruptedException {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
		
		Thread.sleep(5);
	}
	
	@AfterMethod
	public void quitBrowser() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
	
	@Test(description = "R0007-TC01 Open \"Help & Contacts\" in new window")
	public void testChildWindowHandle() {
		
		driver.findElement(By.xpath("//a[text()=' Help & Contact']")).sendKeys(Keys.CONTROL, Keys.ENTER);
	
		// get all the window handle ids 
		Set<String> windowIDs = driver.getWindowHandles();
		
		Iterator<String> windowIDsIterator = windowIDs.iterator();
		windowIDsIterator.next();
		//driver.switchTo().window(firstOpenedWindow);
		System.out.println(driver.getTitle());
		
		driver.switchTo().window(windowIDsIterator.next());
		System.out.println(driver.getTitle());
		driver.close();
	}
}
