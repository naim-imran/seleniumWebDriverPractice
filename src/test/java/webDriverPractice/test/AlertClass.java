package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import webDriverPractice.initialization.InitialComponents;

public class AlertClass {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void initialSetup() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("http://demo.guru99.com/V1/index.php");
		driver.manage().window().maximize();
	}

	@Test
	public void alertClassTest() throws InterruptedException {
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("naim");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		Thread.sleep(2000L);

		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		Thread.sleep(5000L);
		driver.quit();
	}

}
