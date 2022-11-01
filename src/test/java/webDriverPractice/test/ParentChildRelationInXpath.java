package webDriverPractice.test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webDriverPractice.initialization.InitialComponents;

public class ParentChildRelationInXpath {
    WebDriver driver;
    @BeforeMethod
    public void setupInitialComponent(){
    	InitialComponents initialComponents = new InitialComponents();
		driver = initialComponents.launchBrowser();
        
    }

    @Test
    public void parentToChildTest(){

    }
}
