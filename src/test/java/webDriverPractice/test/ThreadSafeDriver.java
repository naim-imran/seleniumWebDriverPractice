package webDriverPractice.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.Test;


/*
 * When we run our test in sequential mode there is no issue with thread safety. 
 * Problem arises when we run our test in parallel mode 
 * because multiple instances try to access the Webdriver class members at the same time. 
 * We can mitigate this issue by using threadLocal java class, synchronized specifier and selenium class threadGuard, 
 * Java ThreadLocal class provides thread-local variables. 
 * It enables us to create variables that can only be read and write by the same thread means 
 * if we set the webdriver as thread-local, the instances that are trying to access it will get separate copy of it.
 */
public class ThreadSafeDriver {
	
	private ThreadLocal<WebDriver> threadLocaldriver;
	private Properties prop;
	private WebDriver driver;
	private boolean insesureCertificate= true;

	public Properties loadProperty() {
		try {
			FileInputStream fs = new FileInputStream(System.getProperty("user.dir")
					+ "\\src\\main\\java\\webDriverPractice\\utilities\\config.properties");
			prop = new Properties();
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	public ThreadLocal<WebDriver> setBrowser() {
		threadLocaldriver = new ThreadLocal<>();
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: loadProperty().getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")&& threadLocaldriver.get()==null) {
			ChromeOptions co = new ChromeOptions();
			co.setBrowserVersion("115");
			co.addArguments("--remote-allow-origins=*");
			co.setAcceptInsecureCerts(insesureCertificate);
			co.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
			threadLocaldriver.set(ThreadGuard.protect(new ChromeDriver(co)));
			return threadLocaldriver;
		} else if (browserName.equalsIgnoreCase("firefox") && threadLocaldriver.get()==null) {
			threadLocaldriver.set(ThreadGuard.protect(new FirefoxDriver()));
			return threadLocaldriver;
		} else {
			return null;
		}
	}

	public WebDriver launchBrowser() {
		long implicitWaitTime = Long.parseLong(loadProperty().getProperty("implicitWaitTime"));
		driver = setBrowser().get();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWaitTime));
		driver.manage().window().maximize();
		return driver;
	}

	public void tearDownThreadSafeBrowser() {
		
		threadLocaldriver.remove();
		driver.quit();
	}

	
	
	
	@Test
	public void testThreadSafeDriver() {
		WebDriver driver = launchBrowser();
		driver.get("https://www.google.com/");
		tearDownThreadSafeBrowser();
	}

}
