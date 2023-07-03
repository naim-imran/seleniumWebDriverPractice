package webDriverPractice.test;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class R0019_ThreadSafeDriver {
	protected static final ThreadLocal<WebDriver> threadLocaldriver = new ThreadLocal<>();
	private Properties prop;
	
	public synchronized Properties loadProperty() {
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
		return prop;
	}

	private ThreadLocal<WebDriver> setBrowser() {
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): loadProperty().getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			threadLocaldriver.set(ThreadGuard.protect(new ChromeDriver(co)));
			threadLocaldriver.get().manage().window().maximize();
			return threadLocaldriver;
		}else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			threadLocaldriver.set(ThreadGuard.protect(new FirefoxDriver()));
			return threadLocaldriver;
		}
		else {
			return null;
		}
		
		
	}
	
	public synchronized WebDriver launchBrowser() {
		long implicitWaitTime = Long.parseLong(loadProperty().getProperty("implicitWaitTime"));
		WebDriver driver = setBrowser().get();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
		driver.manage().window().maximize();
		return driver;
	}
	
	public void removeThreadSafeBrowser(WebDriver driver) {
		driver.quit();
		threadLocaldriver.remove();
	}

	
	@Test
	public void testThreadSafeDriver() {
		WebDriver driver = launchBrowser();
	driver.get("https://www.google.com/");
	driver.quit();
	}
	@AfterMethod
	public void quitBrowser() {
		threadLocaldriver.remove();
	}
	
}
