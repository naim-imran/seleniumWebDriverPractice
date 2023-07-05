package webDriverPractice.listeners;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

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

import webDriverPractice.utilities.InitialComponents;

public class Listeners extends InitialComponents implements ITestListener{
	
	private ExtentReports reporter;
	private ExtentTest log;
	private WebDriver driver ;
	@Override
	public void onStart(ITestContext context) {
		reporter = InitialComponents.getReport();
	}

	@Override
	public void onFinish(ITestContext context) {
		reporter.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		log = reporter.createTest(result.getMethod().getMethodName());
		
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " is passed"); 
		log.log(Status.PASS, " passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String name = result.getMethod().getMethodName();
		System.out.println(name); 
		log.fail(result.getThrowable());
		
	
		
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
		
			e.printStackTrace();
		}
		

		String time =LocalDateTime.now().toString(); 
		// take the screenshot using getScreenShotAs() method and store it in variable as file.
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// copy the file using copyFile() method in FileUtils class.
		try {
			FileUtils.copyFile(srcFile, new File (System.getProperty("user.dir") + "//src//test//java//screenShots//"+name+"-"+ time + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			log.addScreenCaptureFromPath(System.getProperty("user.dir") +"//src//test//java//screenShots//"+name+"-"+ time + ".png" , name);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " skipped"); 
		log.skip(result.getThrowable());
		
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
