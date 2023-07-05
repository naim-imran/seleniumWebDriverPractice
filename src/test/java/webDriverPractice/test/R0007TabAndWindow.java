package webDriverPractice.test;

import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0007TabAndWindow {
	private WebDriver driver;

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
		
		String helpContactLink = driver.findElement(By.xpath("//a[text()=' Help & Contact']")).getAttribute("href");
		driver.switchTo().newWindow(WindowType.TAB);
		Iterator<String> it = driver.getWindowHandles().iterator();
		String parentWindow = it.next();
		
		
		driver.get(helpContactLink);
		System.out.println("child window: " + driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		driver.switchTo().window(parentWindow);
		System.out.println("Parent window: " + driver.getTitle());
		System.out.println(driver.getCurrentUrl());
		
		

//		// get all the window handle ids 
//		Set<String> windowIDs = driver.getWindowHandles();
//		Iterator<String> windowIDsIterator = windowIDs.iterator();
//		driver.switchTo().window(windowIDsIterator.next());
//		System.out.println("child window: " + driver.getTitle());
//		System.out.println(driver.getCurrentUrl());
//		driver.switchTo().window(parentWindow);
//		System.out.println(driver.getCurrentUrl());
	}
	
	
}
