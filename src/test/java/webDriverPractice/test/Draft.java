package webDriverPractice.test;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webDriverPractice.initialization.InitialComponents;

public class Draft {

	public static void main(String[] args) throws MalformedURLException, IOException {
		InitialComponents initialComponents = new InitialComponents();
		
		WebDriver driver = initialComponents.launchBrowser();
		
		driver.get("https://www.ebay.com");
		
		List<WebElement> url = driver.findElements(By.tagName("a"));
		System.out.println( "total link " + url.size());
		
		for (int i =0; i<=url.size(); i++) {
			
			String url1 = url.get(i).getAttribute("href");
			
			HttpsURLConnection connection = (HttpsURLConnection) new URL(url1).openConnection();
			connection.setRequestMethod("HEAD");
			connection.connect();
			
			System.out.println(i + " = " + connection.getResponseCode());
			
		}
		driver.quit();
	}

}
//  "https://www.ebayinc.com/company/"