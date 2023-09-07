package webDriverPractice.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

public class DrawBorderAroundElement extends InitialComponents{
	 WebDriver driver;
	private Object executionTime;
	

		@BeforeMethod
		public void setupInitialComponents() throws InterruptedException {
			driver = new ThreadSafeDriver().launchBrowser();
			
			driver.get("https://www.ebay.com/");
			
			Thread.sleep(5);
			executionTime = getCurrentTimeToFormatedString();
			
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
		public void testDrawBorder() throws IOException {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			// executeScript() method is used to execute javaScript code.
			js.executeScript("arguments[0].style.border='4px solid red'", driver.findElement(By.
					xpath("//div[@id='gh-ac-box']/parent::td")));
			
			String name = Thread.currentThread().getStackTrace()[1].getMethodName();
			

			// take the screenshot using getScreenShotAs() method and store it in variable as file.
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			// copy the file using copyFile() method in FileUtils class.
			FileUtils.copyFile(srcFile, new File (System.getProperty("user.dir") + "\\src\\test\\java\\screenShots\\"+name+"_"+executionTime + ".png"));
			driver.quit();
			
			System.out.println("Please refresh prokect to see the Screenshots");
			System.out.println("Screenshot is saved into " + System.getProperty("user.dir") + "\\src\\test\\java\\screenShots");
			
		}
		
		
		
}
