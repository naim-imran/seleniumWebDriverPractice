package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0003_CSSselector {

	 WebDriver driver;


	@BeforeMethod
	public void setupInitialComponents() {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
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
	
	
	@Test(description = "TC015 locate \"search\" using partial text of an attribute value using"
			+ " regular expression by CssSelector")
	public void testCssSelectorWithPartialText() {
		driver.findElement(By.cssSelector("input[class*='autocomplete-input']")).sendKeys("laptops");
	}
}
