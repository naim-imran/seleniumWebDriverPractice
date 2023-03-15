package webDriverPractice.initialization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialComponents {
	public WebDriver driver;

	public WebDriver launchBrowser() {
		/*
		 * WebDriverManager.chromedriver().setup(); driver = new ChromeDriver();
		 */
		
		WebDriverManager.firefoxdriver().setup();
		
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		return driver;
		
		

	}

}
