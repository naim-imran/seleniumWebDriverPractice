package webDriverPractice.test;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

public class R0009Synchronization {
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
	
	
	@Test(description = "R0009-TC001 set global wait time using implicitlyWait()")
	public void testImplicitlyWait() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		System.out.println(driver.getTitle()); 
	}
	
	@Test(description = "R0009-TC002 wait 10 seconds for text \"Introducing watches expert verification\" using WebDriverWait()")
	public void testWebDriverWait() {
		WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
		
		for (byte i=0; i<6; i++ ) {
			try {
				webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Hanes and Champion deals')]"))).click();
				if (driver.getTitle().equalsIgnoreCase("Fresh deals on Hanes and Champion | eBay. Save an additional 20% on purchases over $15.")) {
					break;
				}
				System.out.println(driver.getTitle());
			} catch (Exception e) {
				driver.navigate().refresh();
			}	
		}
		
	}
	
	@Test(description = "R0009-TC003 wait for 20 seconds at interval of 3 seconds to locate \"Get Your Things\" link button")
	public void testFluentWait() {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(20))
				.pollingEvery(Duration.ofSeconds(3)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath("//a[@href='https://www.ebay.com/mys/home?source=GBH']"))).click();
	}
	
}
