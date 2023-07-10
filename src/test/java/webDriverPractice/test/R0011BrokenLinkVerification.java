package webDriverPractice.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import webDriverPractice.utilities.InitialComponents;

public class R0011BrokenLinkVerification {
	public  WebDriver driver;
	 private SoftAssert SoftAssert  = new SoftAssert();
	 
	public WebDriver getDriver() {
		return driver;
		
	}

	/**
	 * @throws InterruptedException
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	@Test(priority = 1, description = "R0011-TC01 CareFirst homepage link test")
	public void testBrokenLink() throws InterruptedException, MalformedURLException, IOException {

		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://individual.carefirst.com/individuals-families/plans-coverage/medical/medicaid-plans.page");

	

		
		/*
		 * WebElement objIndividual = driver.findElement(By.
		 * xpath("//div[@id='leftNav']//a[contains(text(),'Individual & Family Plans')]"
		 * )); String toolTipText = objIndividual.getAttribute("title");
		 * System.out.println(toolTipText);
		 * 
		 * if (toolTipText.equalsIgnoreCase("Individual & Family Plans")) {
		 * System.out.println("tooltip test passed"); }
		 */
		 

		String parentWindowHandle = driver.getWindowHandle();// getting only the current window handle by
		// getWindowHandle not getWindowHandles
		List<WebElement> allFooterLinks = driver
				.findElements(By.xpath("//div[@class='iw_component']//footer//section//li/a"));
		List<WebElement> footerLinks1 = driver
				.findElements(By.xpath("//div[@class='iw_component']//footer//section//p/a"));

		allFooterLinks.addAll(footerLinks1);
		System.out.println("Total Links in Footer is: " + (allFooterLinks.size()));
		System.out.println(System.getProperty("os.name"));
		for (int i = 0; i < allFooterLinks.size(); i++) {
			//			HttpsURLConnection connection = (HttpsURLConnection) new URL(allFooterLinks.get(i).getAttribute("href")).openConnection();
			//			connection.setRequestMethod("HEAD");
			//			connection.connect();
			//			System.out.println(connection.getResponseCode());
			//			
			
			if (System.getProperty("os.name").contains("Mac")) {
				allFooterLinks.get(i).sendKeys(Keys.COMMAND, Keys.ENTER);
			}else {
				allFooterLinks.get(i).sendKeys(Keys.CONTROL, Keys.ENTER);
			}
				
			
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

	@Test(priority=0)
	public void testBrokenListUsingHttpsURLConnection() throws MalformedURLException, IOException {
	InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com");
		// get all the footer link in a list of web element
		List<WebElement> url = driver.findElements(By.xpath("//footer[@id='glbfooter'and @class='gh-w']//a[@class='thrd']"));
		System.out.println( "total link " + url.size());
		List<String> brokenLink =new ArrayList<String>();
		
		int i;
			for (i =0; i<url.size(); i++) {
				String url1 = url.get(i).getAttribute("href");
				HttpsURLConnection connection = (HttpsURLConnection) new URL(url1).openConnection();
				connection.setRequestMethod("HEAD");
				connection.connect();
				int responseCode = connection.getResponseCode();
				System.out.println(i+1 + " = \" "+ url.get(i).getText() + "\"  Status code "+ responseCode);
				if (responseCode>400) {
					brokenLink.add(url.get(i).getText());
				}
				if (i==32) {
					SoftAssert.assertTrue(false);
				}
			}
		System.out.println("Total broken link "+brokenLink.size());
		brokenLink.stream().forEach(b->System.out.println(b));
		SoftAssert.assertAll();
		}
	
		@AfterMethod
		public void quitBrowser() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			driver.quit();
		}
	}
