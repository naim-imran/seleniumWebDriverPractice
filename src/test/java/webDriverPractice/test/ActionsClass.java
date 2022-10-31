package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webDriverPractice.initialization.InitialComponents;

public class ActionsClass {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void initialSetup() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://www.amazon.com/");
		 action = new Actions(driver);
	}

	@Test(priority=1)
	public void mouseOverTest() {
		action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"))).build()
				.perform();
	}

	@Test(priority=2)
	public void keyboardInteractionTest() {
		action.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click()
				.keyDown(Keys.SHIFT).sendKeys("razer blade").build().perform();
	}
	
	@AfterClass
	public void quitBrowser() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
