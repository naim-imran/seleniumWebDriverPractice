package webDriverPractice.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InitialComponents {
	private ExtentSparkReporter reporter;
	private static ExtentReports logReport;
	private String time = InitialComponents.getCurrentTimeToFormatedString();
	public static WebDriver driver;
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
	public static synchronized WebDriver launchBrowser() {
		if (getConfigData().getProperty("insecureCertificate").equalsIgnoreCase("true")) {
			insesureCertificate = true;
		}

		String browserName = System.getProperty("browser") != null ? System.getProperty("browser"): getConfigData().getProperty("browser");
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();

			co.addArguments("--remote-allow-origins=*");
			co.setAcceptInsecureCerts(insesureCertificate);
			co.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

			driver = new ChromeDriver(co);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
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

	public static String getCurrentTimeToFormatedString() {
		//return LocalTime.now().toString();
		String currentTime = LocalDateTime.now().toString();
		return ((currentTime.replace(".", "_")).replace(":", "_"));
	}

	public InitialComponents() {
		reporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "\\src\\test\\java\\extentReports\\"+time+".html");
		reporter.config().setDocumentTitle("Title seleniumWebDriverPractice");
		reporter.config().setReportName("Report name " + time);

		logReport = new ExtentReports();
		logReport.attachReporter(reporter);
		logReport.setSystemInfo("Operating System ", System.getProperty("os.name"));
	}

	public static ExtentReports getReport() {
		return logReport;
	}

	
}
