package webDriverPractice.test;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class R0023extendReport {
	private ExtentReports logReport;
	
	
	public  R0023extendReport() {
		ExtentSparkReporter reporter = new ExtentSparkReporter(System.getProperty("user.dir") + "\\src\\test\\java\\extentReports\\"+"index.html");
		reporter.config().setReportName("Extent report practice");
		reporter.config().setDocumentTitle("Extent report");
	
		logReport = new ExtentReports();
		logReport.attachReporter(reporter);
		logReport.setSystemInfo("Tester", "Naayeem");
	
	}
	
	@Test(priority = 2)
	public void testExtentReport() {
		logReport.createTest("testing extent report");
		System.out.println("test 01");
	}
	
	@Test(priority = 1)
	public void testExtentReport2() {
		logReport.createTest("testing extent report 02");
		System.out.println("test 02");
	}
	
	@Test(priority = 0)
	public void testExtentReport03() {
		logReport.createTest("testing extent report03");
		System.out.println("test 03");
	}
	
	@AfterTest
	public void cleanTestBed() {
		logReport.flush();
	}
}
