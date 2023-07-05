package webDriverPractice.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{
	private byte count =1;
	private byte max = 1;
	@Override
	public boolean retry(ITestResult result) {
		if (count<=max) {
			count++;
			return true;
		} 
		return false;
	}

}
