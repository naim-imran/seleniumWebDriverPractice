package webDriverPractice.test.C001_WebDriver_Interface;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class C001_WebDriver_Interface {

	// WebDriver is an Interface which extends the SearchContext Interface.
	// under WebDriver Interface we have implemented class called RemoteWebDriver.
	// RemoteWebDriver also implements TakeScreenshot and JavaScriptExecutor interfaces.
	// under RemoteWebDriver class we have classes like ChromeDriver, FirefoxDriver, EdgeDriver etc.

	
	private WebDriver driver;
	String browserName= "chrome";

	public WebDriver setupDriver() {

        switch (browserName) {
            case "chrome" -> driver = new ChromeDriver();
            case "firefox" -> driver = new FirefoxDriver();
            case "edge" -> driver = new EdgeDriver();
        }
		return driver;
	}
}
