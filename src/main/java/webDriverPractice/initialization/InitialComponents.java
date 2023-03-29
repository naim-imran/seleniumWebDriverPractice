package webDriverPractice.initialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialComponents {
	public WebDriver driver;
	Properties prop;

	public WebDriver launchBrowser() {
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/webDriverPractice/initialization/config.properties");
			prop = new Properties();
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			driver.manage().window().maximize();
			return driver;
		} else if (browserName.equalsIgnoreCase(prop.getProperty("firefox"))) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			return driver;
		}

		else {
			return driver = null;
		}
		
		
		
	}

}
