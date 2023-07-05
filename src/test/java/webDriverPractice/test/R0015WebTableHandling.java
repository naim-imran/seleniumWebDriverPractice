package webDriverPractice.test;

import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0015WebTableHandling {
	 WebDriver driver;

		@BeforeMethod
		public void setupInitialComponents() throws InterruptedException {
			InitialComponents initialComponents = new InitialComponents();
			driver = initialComponents.launchBrowser();
			driver.get("https://rahulshettyacademy.com/AutomationPractice/");
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
		
		
		
		@Test(description = "R0015-TC001 get the data from table01")
		public void testdynamicWebTable() {
			WebElement table = driver.findElement(By.id("product"));
			int rowCount = table.findElements(By.tagName("tr")).size();
			System.out.println("Number of rows " + rowCount);
			WebElement column = driver.findElement(By.xpath("//body//tr[3]"));
			int columnCount = column.findElements(By.tagName("td")).size();
			System.out.println("Number of column " + columnCount);
			
			for (int i=0; i<columnCount; i++) {
				System.out.println("Column#:" + (i+1) + " data " + column.findElements(By.tagName("td")).get(i).getText());
			}
		}
}
