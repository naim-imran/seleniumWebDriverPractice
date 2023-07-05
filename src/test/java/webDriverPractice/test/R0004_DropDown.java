package webDriverPractice.test;

import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class R0004_DropDown {
	WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() throws InterruptedException {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.ebay.com/");
		//driver.manage().deleteAllCookies();
//		driver.findElement(By.xpath("//span[@id='gh-ug']/a[text()='Sign in']")).click();
//		driver.findElement(By.xpath("//input[@id='userid']")).sendKeys("mail4testprojects@gmail.com");
//		driver.findElement(By.xpath("//button[@id='signin-continue-btn']")).click();
//		driver.wait(3000);
////		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='tt']")));
//		driver.findElement(By.xpath("//div/input[@id='pass']")).sendKeys("test54321@$");
		
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

	@Test(description = "R0004-TC001 select \"Cell Phones & Accessories\" from Select  Dropdown using Select class")
	// there are three ways we can select option from drop down with select tag
				//(01)	selectByIndex
				//(02)	selectByValue
				//(03)	selectByVisibleText
	public void testDropdownUsingSelectClass() {
		Select selectDropDown = new Select(driver.findElement(By.name("_sacat")));
		selectDropDown.selectByVisibleText("Cell Phones & Accessories");
	}

	@Test(description = "A004-TC002 test \"Search Box\" with auto suggestive dropdown")
	public void testDynamicDropDownList() throws InterruptedException {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys(Keys.chord("lap"));

		explicitWait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-1']/li/a[contains(@id,'ui-id')]")));
		List<WebElement> suggestionList = driver.findElements(By.xpath("//ul[@id='ui-id-1']/li"));

		for (WebElement element : suggestionList) {
			String text = element.findElement(By.tagName("a")).getAttribute("aria-label");
			if (text.contains("laptop i5")) {
				element.click();
				break;

			}
		}
	}

}
