package webDriverPractice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;

import webDriverPractice.initialization.InitialComponents;

public class AlertClass {
	WebDriver driver;
	Actions action;
	@BeforeClass
	public void initialSetup() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://www.amazon.com/");
		 action = new Actions(driver);
	}

}
