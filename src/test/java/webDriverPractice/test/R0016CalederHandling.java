package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0016CalederHandling {
	public class R0015_WebTableHandling {
		WebDriver driver;

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
		public void testDynamicCalendar() {
			String expectedMonthName = "April";
			String day = "19";
			driver.findElement(By.xpath("//button[@id='d1-btn']")).click();
			WebElement calender = driver.findElement(By.xpath("//div[@class='uitk-calendar']"));
			for (WebElement iterator : calender.findElements(By.xpath("//h2[@class='uitk-date-picker-month-name uitk-type-medium']"))) {
				String monthName = iterator.getText();
				System.out.println(monthName);
				if (monthName.contains(expectedMonthName)) {
					iterator.findElement(By.xpath("//table[@class='uitk-date-picker-weeks']/child::tbody//button[@data-day='"+day+"']")).click();
					
					break;
				}
				
			}
		}
			
		
	}
}
