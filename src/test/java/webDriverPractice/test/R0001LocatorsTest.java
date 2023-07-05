package webDriverPractice.test;


import static org.openqa.selenium.support.locators.RelativeLocator.with;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/* as the name suggest, it is used to locate web element in html DOM. There are eight different types of locators 
 
							   1. Id 
							   2. Name 
							   3. ClassName 
							   4. TagName 
							   5. LinkText 
							   6. PartialLinkText 
							   7. Xpath 
							   8. CSS
 * 
 * *. in selenium version 4 relative locators also added which includes 
 * 
 * 								1.above(), 
 * 								2.below(), 
 * 								3.toRightOf() and 
 * 								4.toLeftOf() methods.
 * 
 * 		to use relative locators we have to manually import (org.openqa.selenium.support.locators.RelativeLocator.with(By by))
 * 
 * 		we also can find webelement by using javaScriptExecutor
 */

public class R0001LocatorsTest {
	private WebDriver driver;

	@BeforeMethod
	public void setupInitialComponents() {
		/*
		 * InitialComponents initialComponents = new InitialComponents();
		 *  driver = initialComponents.launchBrowser();
		 */
		driver = new R0019ThreadSafeDriver().launchBrowser();
		driver.get("https://www.ebay.com/");
	}

	@Test(description = "TC001 locate ebay logo by ID")
	public void id_LocatorTest() {
		System.out.println(driver.findElement(By.id("gh-logo")).isDisplayed());
	}

	@Test(description = "TC002 expand catagory filter list by using name locator")
	public void name_LocatorTest() {
		driver.findElement(By.name("_sacat")).click();
	}
	
	@Test(description = "TC003 print number of input tag present in the DOM by tagName")
	public void tagName_LocatorTest() {
		System.out.println(driver.findElements(By.tagName("input")).size());
	}
	
	@Test(description = "TC004 type \"laptops\" in search textbox by xpath")
	public void xpath_LocatorTest() {
		/*
		 * Relative XPath syntax: 
								//tagName[@attributeName=’attribute value’]
		Double forward slash then tag name then inside the square bracket @ then attribute name = and then within a single quotation attribute value. For tag and attribute, we can use a regular expression.

		 */
		driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("laptops");
	}
	
	@Test(description = "TC005 type \"mobile\" in search textbox by cssSelector")
	public void cssSelector_LocatorTest() {
		/*
		 * syntax:  TagName[attribute=’value’] 
			For attribute ID, we can also write it as tagName#attributeValue
			input#identifierId
			we can also skip the tagName
			#identifierId
			For attribute class,  syntax is tagName.attributeValue. if the class attribute value has whitespace, replace that with “.”
			<input class="gNO89b xyz" value="Google Search" aria-label="Google Search" name="btnK" role="button" tabindex="0" type="submit" data-ved="0ahUKEwjlpbOlyJj5AhWHM1kFHdEkDEAQ4dUDCA0">
			
			Syntax        attributeName.attributeValue
			Input.gN089b.xyz
			or
			Syntax        .attributeValue
			.gN089b.xyz

		 */
		driver.findElement(By.cssSelector("input[id='gh-ac']")).sendKeys("mobile");
	}
	
	@Test(description = "TC006 click on search button using className locator")
	public void className_LocatorTest() {
		driver.findElement(By.className("btn-prim")).click();
	}
	
	@Test(description = "TC007 click on Electronics link button using linkTest locator")
	public void linkText_LocatorTest() {
		driver.findElement(By.linkText("Electronics")).click();
	}
	
	@Test(description = "TC008 click on \"Home & Garden\" link button using partialLink locator")
	public void partialLinkText_LocatorTest() {
		driver.findElement(By.partialLinkText("Home & Gard")).click();
	}
	
	@Test(description = "TC009 click on \"sign in\" link button using relative locator above()")
	public void relativeLocator_above() {
		/*to use relative locators we have to manually import (org.openqa.selenium.support.locators.RelativeLocator.*  */
		driver.findElement(with(By.xpath("//span[@id='gh-ug']")).above(driver.findElement(By.xpath("//a[@id='gh-la']")))).click();
	}
	
	@Test(description = "TC010 click on \"Advance\" link below cart using relative locator below()")
	public void relativeLocatorBelow() {
		 WebElement cart = driver.findElement(By.xpath("//a[@href= 'https://cart.ebay.com']"));
	      WebElement link = driver.findElement(RelativeLocator.with(By.tagName("a")).below(cart));
	      link.click();
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
