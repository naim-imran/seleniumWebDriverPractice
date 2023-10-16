package webDriverPractice.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class C001_WebDriver_Interface {

	// WebDriver is an Interface which extends the SearchContext Interface.
	// under WebDriver Interface we have implemented class called RemoteWebDriver.
	// RemoteWebDriver also implements TakeScreenshoot and JavaScriptExecutor interfaces.
	// under RemoteWebDriver class we have classes like ChromeDriver, FirefoxDriver, EdgeDriver etc.

	private String browserName = "edge";
	private WebDriver driver;

	public WebDriver setupDriver() {

		if (browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		return driver;
	}
}
