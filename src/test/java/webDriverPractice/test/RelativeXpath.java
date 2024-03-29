package webDriverPractice.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RelativeXpath {
    WebDriver driver;
    @BeforeMethod
    public void setupInitialComponent(){
    	ThreadSafeDriver initialComponents = new ThreadSafeDriver();
		driver = initialComponents.launchBrowser();
		driver.get("https://www.ebay.com/");
    }
    
    //  In XPath parent-child relationship is used to traverse from parent to child
    //  node or child to parent node using different methods like parent::, child::, 
    //  following-sibling:: ,  ancestor:: , preceding sibling:: , descendant:: .

    @Test(description = "R0002-TC001 traversing from parent to child node using( child:: ) ")
    public void parentToChildTestUsingChild(){
		driver.findElement(By.xpath("//div[@id='gh-ac-box2']/child::input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "R0002-TC002 traversing from parent to child node using( ForwardSlash ) ")
    public void parentToChildTestUsingForwardSlash(){
		driver.findElement(By.xpath("//div[@id='gh-ac-box2']/input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "R0002-TC003 locating search box using partial text of an attribute value by xpath ")
    public void testUsingContainsMethodWithxpath(){
		driver.findElement(By.xpath("//div[contains(@id,'-ac-bo')]/input[@id='gh-ac']")).sendKeys("laptops");

    }
    
    @Test(description = "R0002-TC004 locate \"Score these trending kicks element\" link text using text() method by xpath")
    public void testUsingTextFunctionWithXpath() {
    	driver.findElement(By.xpath("//a[text()='Score these trending kicks']")).click();
    }
    
    @Test(description = "R0002-TC005 locate \"Shop by catagory\" using following-sibling:: concept by xpath")
    public void testUsingFollowingsibkingByXpath(){
    	driver.findElement(By.xpath("//div[@id='gh-shop']/button/following-sibling::div[@class='gh-o']/preceding-sibling::button")).click();
    }
    
    
    @Test(description = "R0002-TC006 locate \" Motor \" link using parent:: concept by xpath")
    public void testUsingParentKeywordByXpath(){
    	driver.findElement(By.xpath("//div[@class='hl-cat-nav__expander']/parent::li[@class='hl-cat-nav__js-tab']"
    			+ "/child::a[@href='https://www.ebay.com/b/Auto-Parts-and-Vehicles/6000/bn_1865334']")).click();
    }
    
    @AfterMethod
    public void quitDriver() throws InterruptedException {
    	Thread.sleep(3000);
    	driver.quit();
    }
}
