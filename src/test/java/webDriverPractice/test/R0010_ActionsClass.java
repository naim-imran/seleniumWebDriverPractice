package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import webDriverPractice.utilities.InitialComponents;

public class R0010_ActionsClass {
	
	WebDriver driver;
	Actions action;

	

	@Test(priority=1, description = "R0010-TC001 hover mouse on \"Hello, sign in, Account & List\"", retryAnalyzer = webDriverPractice.listeners.Retry.class)
	public void testMouseHover() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://www.amazon.com/");
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'Account & Lists')]"))).build()
				.perform();
	}

	@Test(priority=2, description = "R0010-TC002 Type \"razer blade\" in capital in search edit box" , retryAnalyzer = webDriverPractice.listeners.Retry.class)
	public void keyboardInteractionTest() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://www.amazon.com/");
		action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"))).click()
				.keyDown(Keys.SHIFT).sendKeys("razer blade").keyDown(Keys.ENTER).build().perform();
	}
	
	@Test(priority = 4, description = "R0010-TC004 Type \"laptop\" in capital in search edit box using \"Keys.chord(\"laptop\")\" ", retryAnalyzer = webDriverPractice.listeners.Retry.class )
	public void testkeyboardInteraction01() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://www.amazon.com/");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(Keys.SHIFT, Keys.chord("laptop"), Keys.ENTER);
		
	}
	
	@Test(priority=3, description = "R0010-TC003 test the drag funtionality of dragable box", retryAnalyzer = webDriverPractice.listeners.Retry.class)
	public void TestDragNDrop() {
		InitialComponents initialcomponent = new InitialComponents();
		driver = initialcomponent.launchBrowser();
		driver.get("https://jqueryui.com/droppable/");
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class='demo-frame']")));
		System.out.println(driver.findElement(By.xpath("//div[@id='droppable']")).getText());
		
		// performing drag and drop
		action = new Actions(driver);
		action.dragAndDrop(driver.findElement(By.xpath("//*[@id=\"draggable\"]")), driver.findElement(By.xpath("//div[@id='droppable']"))).build().perform();
		System.out.println(driver.findElement(By.xpath("//p[contains(text(),'Dropped!')]")).getText());
		
		// switching to default content
		System.out.println(driver.switchTo().defaultContent().findElement(By.xpath("//h1[@class='entry-title']")).getText());
		
	}
	
	@AfterMethod
	public void quitBrowser() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}
}
