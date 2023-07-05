package webDriverPractice.test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

public class R0008TakeScreenShots{
	

	private WebDriver driver;
	
	@BeforeMethod
	public void setupInitialComponents() throws InterruptedException {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
		initialComponents.getMethodName();
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
	
	@Test(description = "R0008-TC01 take screenShot of ebay Home page")
	public void testTakeScreenShot() throws IOException {
		
		String name = Thread.currentThread().getStackTrace()[1].getMethodName();
		
		
		String screenShotName =LocalDateTime.now().toString(); 
		// take the screenshot using getScreenShotAs() method and store it in variable as file.
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copy the file using copyFile() method in FileUtils class.
		FileUtils.copyFile(srcFile, new File (System.getProperty("user.dir") + "//src//test//java//screenShots//"+name+"-"+screenShotName + ".png"));
		driver.quit();
		
		System.out.println("Please refresh prokect to see the Screenshots");
		System.out.println("Screenshot is saved into " + System.getProperty("user.dir") + "//src//test//java//screenShots");	
		
	}
	
	@Test(description = "R0008-TC01 take screenShot of an webElement")
	public void selenium4testTakeScreenShot() throws IOException {
		String name = Thread.currentThread().getStackTrace()[1].getMethodName();
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("laptops");
		String screenShotName =LocalDateTime.now().toString(); 
		File srcFile = driver.findElement(By.cssSelector(".gh-tbl2")).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcFile, new File (System.getProperty("user.dir") + "//src//test//java//screenShots//"+name+"-"+screenShotName + ".png"));
		
		
		System.out.println("Please refresh prokect to see the Screenshots");
		System.out.println("Screenshot is saved into " + System.getProperty("user.dir") + "//src//test//java//screenShots");	
		
	}
	
}
