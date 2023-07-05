package webDriverPractice.test;
import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0006_AlertClass {
	WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() throws InterruptedException {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("http://demo.guru99.com/V1/index.php");
		
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

	@Test(description = "R0006-TC01 accept window alert")
	public void alertClassTest() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("naim");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Thread.sleep(2000L);

		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
	}

}
