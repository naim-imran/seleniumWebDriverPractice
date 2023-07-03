package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import webDriverPractice.initialization.InitialComponents;

public class R0011_BrokenLinkVerification {
	WebDriver driver;

	@Test(priority = 1, description = "R0011-TC01 CareFirst homepage link test")
	public void testBrokenLink() throws InterruptedException, MalformedURLException, IOException {

		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://individual.carefirst.com/individuals-families/plans-coverage/medical/medicaid-plans.page");

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		WebElement objIndividual = driver
				.findElement(By.xpath("//div[@id='leftNav']//a[contains(text(),'Individual & Family Plans')]"));
		String toolTipText = objIndividual.getAttribute("title");
		System.out.println(toolTipText);

		if (toolTipText.equalsIgnoreCase("Individual & Family Plans")) {
			System.out.println("tooltip test passed");
		}

		String parentWindowHandle = driver.getWindowHandle();// getting only the current window handle by
		// getWindowHandle not getWindowHandles
		List<WebElement> allFooterLinks = driver
				.findElements(By.xpath("//div[@class='iw_component']//footer//section//li/a"));
		List<WebElement> footerLinks1 = driver
				.findElements(By.xpath("//div[@class='iw_component']//footer//section//p/a"));

		allFooterLinks.addAll(footerLinks1);
		System.out.println("Total Links in Footer is: " + (allFooterLinks.size()));

		for (int i = 0; i < allFooterLinks.size(); i++) {
			//			HttpsURLConnection connection = (HttpsURLConnection) new URL(allFooterLinks.get(i).getAttribute("href")).openConnection();
			//			connection.setRequestMethod("HEAD");
			//			connection.connect();
			//			System.out.println(connection.getResponseCode());
			//			

			allFooterLinks.get(i).sendKeys(Keys.CONTROL, Keys.ENTER);
			Set<String> whnd = driver.getWindowHandles();// Getting all the window handles by getWindowHandles
			for (String childWindowHandle : whnd) {
				if (!parentWindowHandle.equals(childWindowHandle)) {
					driver.switchTo().window(childWindowHandle);
					Thread.sleep(2000);
					System.out.println((i + 1) + " Page Title: " + driver.getTitle());
					System.out.println("     Link: " + driver.getCurrentUrl());
					driver.close();
				}
				driver.switchTo().window(parentWindowHandle);
			}
		}
	}

	@Test(retryAnalyzer = webDriverPractice.listeners.Retry.class)
	public void testBrokenListUsingHttpsURLConnection() throws MalformedURLException, IOException {
	InitialComponents initialComponents = new InitialComponents();
		
		driver = initialComponents.launchBrowser();
		
		driver.get("https://www.ebay.com");
		
		List<WebElement> url = driver.findElements(By.xpath("//footer[@id='glbfooter'and @class='gh-w']//a[@class='thrd']"));
		System.out.println( "total link " + (url.size()-1));
		
int i;

		for ( i =1; i<url.size(); i++) {

			
			String url1 = url.get(i).getAttribute("href");
			
			
			HttpsURLConnection connection = (HttpsURLConnection) new URL(url1).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			
			System.out.println(i + " = \""+ url.get(i).getText() + "\"      Status code "+ connection.getResponseCode());
			if (i==32) {
				Assert.assertTrue(false);
			}
		}
	

		}
	

		@AfterMethod
		public void quitBrowser() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			driver.quit();
		}
	}
