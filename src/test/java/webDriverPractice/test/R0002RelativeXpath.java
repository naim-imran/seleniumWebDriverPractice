package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webDriverPractice.initialization.InitialComponents;

public class R0002RelativeXpath {
    WebDriver driver;
    @BeforeMethod
    public void setupInitialComponent(){
    	InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
    }
    
    //  In XPath parent-child relationship is used to traverse from parent to child
    //  node or child to parent node using different methods like parent::, child::, 
    //  following-sibling:: ,  ancestor:: , preceding sibling:: , descendant:: .

    @Test(description = "traversing from parent to child node using( child:: ) ")
    public void parentToChildTestUsingChild(){
		driver.findElement(By.xpath("//div[@id='gh-ac-box2']/child::input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "traversing from parent to child node using( ForwardSlash ) ")
    public void parentToChildTestUsingForwardSlash(){
		driver.findElement(By.xpath("//div[@id='gh-ac-box2']/input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "locating search box using partial text of an attribute value by xpath ")
    public void testUsingContainsMethodWithxpath(){
		driver.findElement(By.xpath("//div[contains(@id,'-ac-bo')]/input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "TC011 locate \"Score these trending kicks element\" link text using text() method by xpath")
    public void testUsingTextFunctionWithXpath() {
    	driver.findElement(By.xpath("//a[text()='Score these trending kicks']")).click();
    }
    @AfterMethod
    public void quitDriver() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.quit();
    }
}
