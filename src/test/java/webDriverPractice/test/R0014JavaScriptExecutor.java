package webDriverPractice.test;

import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0014JavaScriptExecutor {
	 WebDriver driver;

		@BeforeMethod
		public void setupInitialComponents() throws InterruptedException {
			InitialComponents initialComponents = new InitialComponents();
			driver = initialComponents.launchBrowser();
			driver.get("https://www.ebay.com/");
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
		
		@Test(priority = 1, description = "locate search box and enter laptops using javaScripptxecutor")
		public void testJavaScriptExecutor() {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// executeScript() method is used to execute javaScript code.
			js.executeScript("document.getElementById('gh-ac').value='laptop'");
		}
}
