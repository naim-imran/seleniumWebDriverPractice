package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0005_DrawBorder {
	 WebDriver driver;

		@BeforeMethod
		public void setupInitialComponents() throws InterruptedException {
			InitialComponents initialComponents = new InitialComponents();
			driver = initialComponents.launchBrowser();
			driver.get("https://www.ebay.com/");
			//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			Thread.sleep(5);
		}
		
		@AfterMethod
		public void quitBrowser() {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			driver.quit();
		}
		
		
		@Test(description = "R0006-TC001 draw border around the \"Search box\"")
		public void testDrawBorder() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// executeScript() method is used to execute javaScript code.
			js.executeScript("arguments[0].style.border='8px solid red'", driver.findElement(By.
					xpath("//div[@id='gh-ac-box']/parent::td")));
			
		}
		
		
		
}
