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
    
    //In XPath parent-child relationship is used to traverse from parent to child
    //node or child to parent node using different methods like parent:: ,
    //following-sibling:: ,  ancestor:: , preceding sibling:: , descendant:: .

    @Test
    public void parentToChildTest(){
    	

    }
}
