package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0018_checkBoxHandling {
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
	
	@Test(description = "TC-001 check the correctness of filter check box")
	public void testCheckBox() {
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Apple watch");
		driver.findElement(By.xpath("//input[@id='gh-btn']")).click();
		if (!(driver.findElement(By.xpath("(//input[@aria-label='Apple Watch Series 7' and @type='checkbox'])[1]")).isSelected())) {
			driver.findElement(By.xpath("(//input[@aria-label='Apple Watch Series 7' and @type='checkbox'])[1]")).click();
		}
		boolean checked = driver.findElement(By.xpath("(//input[@aria-label='Apple Watch Series 7' and @type='checkbox'])[1]")).isSelected();
		AssertJUnit.assertEquals(checked, true);
	}
	

	@Test(description = "TC-002 check the correctness of Radio button")
	public void testRadioButton() throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("Apple watch");
		driver.findElement(By.xpath("//input[@id='gh-btn']")).click();
		if (!(driver.findElement(By.xpath("//span[@class='cbx x-refine__multi-select-cbx' and text()='Buy It Now']/parent::div/parent::div")).isSelected())) {
			driver.findElement(By.xpath("//span[@class='cbx x-refine__multi-select-cbx' and text()='Buy It Now']/parent::div/parent::div")).click();
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Thread.sleep(3000);
		boolean checked = driver.findElement(By.xpath("//input[@aria-label='Buy It Now']")).isSelected();
		AssertJUnit.assertEquals(checked, true);
	}
}
