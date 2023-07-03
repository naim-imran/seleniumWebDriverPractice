package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0016_CalederHandling {
	public class R0015_WebTableHandling {
		WebDriver driver;
		
		public void pickDateExpediaCalender(String expectedDate) throws InterruptedException {
			driver.findElement(By.xpath("//body")).click();
			driver.findElement(By.xpath("//button[@id='d1-btn']")).click();
			WebElement calender = driver.findElement(By.xpath("//div[@class='uitk-date-picker-menu-months-container']"));

			for (WebElement iterator : calender.findElements(By.xpath("//button[@class='uitk-date-picker-day']"))) {
				String monthName = iterator.getAttribute("aria-label");
				System.out.println(monthName);

				  if (monthName.equalsIgnoreCase(expectedDate)) {
					  iterator.click();
					  break;	  
				  }			
			}
		}

		@BeforeMethod
		public void setupInitialComponents() throws InterruptedException {
			InitialComponents initialComponents = new InitialComponents();
			driver = initialComponents.launchBrowser();
			driver.get("https://www.expedia.com/");
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
		

		
		@Test(priority = 1, description = "Select a date from calender")
		public void testDynamicCalendar() throws InterruptedException {
			pickDateExpediaCalender("Apr 24, 2023");
			Thread.sleep(2000);
			pickDateExpediaCalender("May 2, 2023");
		}
				
	}
}
