package webDriverPractice.listeners;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import webDriverPractice.utilities.InitialComponents;

public class ListenersAndReporter extends InitialComponents implements ITestListener{
	
	

	private ExtentSparkReporter reporter;
	private ExtentTest log;
	private WebDriver driver ;
	private ThreadLocal<ExtentTest> threadSafeLog;
	private ExtentReports logReport;
	
	public ListenersAndReporter() {
		 threadSafeLog = new ThreadLocal<ExtentTest>();
		
		// reporter = InitialComponents.getReport();
		 reporter = new ExtentSparkReporter(System.getProperty("user.dir")+ "\\src\\test\\java\\extentReports\\"+getCurrentTimeToFormatedString()+".html");
			reporter.config().setDocumentTitle("Title seleniumWebDriverPractice");
			reporter.config().setReportName("Report name " + getCurrentTimeToFormatedString());

			logReport = new ExtentReports();
			logReport.attachReporter(reporter);
			logReport.setSystemInfo("Operating System ", System.getProperty("os.name"));
	}
	
	@Override
	public void onStart(ITestContext context) {
		
	}

	@Override
	public void onFinish(ITestContext context) {
		logReport.flush();
	}
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		
		  log = logReport.createTest(result.getMethod().getMethodName());
		  threadSafeLog.set(log);
		 
	}
	
	

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " is passed"); 
		threadSafeLog.get().log(Status.PASS, " passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getMethod().getMethodName();
		System.out.println(name + " failed"); 
		threadSafeLog.get().fail(result.getThrowable());
		
	
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		
			e.printStackTrace();
		}
		

		String time = getCurrentTimeToFormatedString();
		// take the screenshot using getScreenShotAs() method and store it in variable as file.
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copy the file using copyFile() method in FileUtils class.
		try {
			FileUtils.copyFile(srcFile, new File (System.getProperty("user.dir") + "\\src\\test\\java\\screenShots\\"+name+"-"+ time + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			log.addScreenCaptureFromPath(System.getProperty("user.dir") +"\\src\\test\\java\\screenShots\\"+name+"-"+ time + ".png" , name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped"); 
		threadSafeLog.get().skip(result.getThrowable());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	

}
