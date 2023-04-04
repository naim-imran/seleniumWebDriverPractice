package webDriverPractice.test;

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

import webDriverPractice.initialization.InitialComponents;

public class R0004_DropDown {
	WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() throws InterruptedException {
		InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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

	@Test(description = "R0004-TC001 select \"Cell Phones & Accessories\" from Select  Dropdown using Select class")
	public void testDropdownUsingSelectClass() {
		Select selectAspeed = new Select(driver.findElement(By.name("_sacat")));
		selectAspeed.selectByVisibleText("Cell Phones & Accessories");
	}

	@Test(description = "A004-TC002 test \"Search Box\" dynamic dropdown list")
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

	@Test(description = "R0004-TC003 verify My eBay drop down funtionality")
	public void testEbayDropDown() throws InterruptedException {
		String parentwindowHandle = driver.getWindowHandle();
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//a[@title='My eBay']"))).build().perform();
		List<WebElement> myEbayDropDown = driver.findElements(By.xpath("//a[@class='gh-eb-oa thrd']"));
		byte counter = 0;
		for (WebElement option : myEbayDropDown) {
			counter++;
			Thread.sleep(2000);
			System.out.println(counter + " " + option.getText());
			option.sendKeys(Keys.CONTROL, Keys.ENTER);
		}
		Set<String> whdls = driver.getWindowHandles();
		
		for( String childWindow : whdls) {
			if (!parentwindowHandle.equals(childWindow)) {
				
				driver.switchTo().window(childWindow);
				Thread.sleep(2000);
				System.out.println("page title "+driver.getTitle());

			}
		}
		
	}

}
