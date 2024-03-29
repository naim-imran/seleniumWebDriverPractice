package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0020FrameAndDragNDropDemo {
	private WebDriver driver;
	ThreadSafeDriver threadSafeDriver = new ThreadSafeDriver();
		
		@BeforeMethod
		public void browserInitialization() {
			
			 driver =threadSafeDriver.launchBrowser();
			 driver.get("https://jqueryui.com/droppable/");
		}
		@AfterMethod
		private void quitBrowser() {
			driver.quit();
		}
		
		
		
		@Test(description = "R0020-TC01 testing the functionality of \"frame\" concept")
		public void testFrame() {
			Actions action = new Actions(driver);
			// Switching to Frame
			driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
			System.out.println(driver.findElement(By.xpath("//div[@id='droppable']")).getText());
			
			// performing drag and drop
			action.dragAndDrop(driver.findElement(By.xpath("//*[@id=\"draggable\"]")), driver.findElement(By.xpath("//div[@id='droppable']"))).build().perform();
			System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Dropped!')]")).getText());
			
			// switching to default content
			System.out.println(driver.switchTo().defaultContent().findElement(By.xpath("//h1[@class='entry-title']")).getText());
			
			threadSafeDriver.tearDownThreadSafeBrowser();
		}	
	
	}


