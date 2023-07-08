package webDriverPractice.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import webDriverPractice.utilities.InitialComponents;

public class Retry implements IRetryAnalyzer{
	
	
	private byte count =1;
	private byte max;
	
public Retry() {
		max = Byte.parseByte(new InitialComponents().getConfigData().getProperty("failedTestRetry"));
	}

	@Override
	public boolean retry(ITestResult result) {
		if (count<=max) {
			count++;
			return true;
		} 
		return false;
	}

}
