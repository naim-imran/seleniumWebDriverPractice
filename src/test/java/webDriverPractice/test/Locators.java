package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

/*
 * There are eight different types of locators 
 * 1. Id 
 * 2. Name 
 * 3. ClassName 
 * 4. TagName 
 * 5. LinkText 
 * 6. PartialLinkText 
 * 7. Xpath 
 * 8. CSS
 */

public class Locators {
	WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() {
		driver = InitialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
	}

	@Test
	public void id_LocatorTest() {
		System.out.println(driver.findElement(By.id("gh-logo")).isDisplayed());
	}

	@Test
	public void name_LocatorTest() {
		System.out.println(driver.findElement(By.name("_sacat")).getText());
	}
	
	@Test
	public void tagName_LocatorTest() {
		//System.out.println(driver.findElement(By.tagName("_sacat")).getText());
	}
	
	@Test
	public void xpath_LocatorTest() {
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("laptops");
	}
	
	@Test
	public void cssSelector_LocatorTest() {
		driver.findElement(By.cssSelector("input[id='gh-ac']")).sendKeys("mobile");
	}
	
	@Test
	public void className_LocatorTest() {
		driver.findElement(By.className("selected navigation-desktop-with-flyout")).click();
	}
	
	@Test
	public void linkText_LocatorTest() {
		driver.findElement(By.linkText("Featured")).click();
	}
	
	@Test
	public void partialLinkText_LocatorTest() {
		driver.findElement(By.partialLinkText("Clothing & Accessor")).click();
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

}
