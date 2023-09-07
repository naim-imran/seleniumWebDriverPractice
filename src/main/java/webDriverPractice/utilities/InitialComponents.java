package webDriverPractice.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;



public class InitialComponents {
	
	
	private WebDriver driver;
	static Properties prop;
	static boolean insesureCertificate = false;
	
	public static Properties getConfigData() {
		try {
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\webDriverPractice\\utilities\\config.properties");
			prop = new Properties();
			prop.load(fs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	public synchronized WebDriver launchBrowser() {
		if (getConfigData().getProperty("insecureCertificate").equalsIgnoreCase("true")) {
			insesureCertificate = true;
		}

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser"): getConfigData().getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			
			ChromeOptions co = new ChromeOptions();
			co.setBrowserVersion("117");

			//co.addArguments("--remote-allow-origins=*");
			co.setAcceptInsecureCerts(insesureCertificate);
			//co.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			driver = new ChromeDriver(co);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("firefox")) {
		
			FirefoxOptions fo = new FirefoxOptions();

			fo.setAcceptInsecureCerts(insesureCertificate);

			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
		}

		else {
			return driver = null;
		}

		return driver;

	}

	public String getMethodName() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace.length >= 3) {
			// The method name is at index 2 in the stack trace
			return stackTrace[2].getMethodName();
		}
		return null;
	}

	public String getCurrentTimeToFormatedString() {
		//return LocalTime.now().toString();
		String currentTime = LocalDateTime.now().toString();
		return ((currentTime.replace(".", "_")).replace(":", "_"));
	}

	

	
}
