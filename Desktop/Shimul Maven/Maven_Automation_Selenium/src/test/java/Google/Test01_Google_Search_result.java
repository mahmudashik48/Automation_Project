package Google;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static ReusableObjects.Reusable_methods.sendKeysMethod;
import static ReusableObjects.Reusable_methods.submitMethod;


public class Test01_Google_Search_result {



    //global or shared variables across methods need to be declared
    //before calling annotations
    WebDriver driver;

    @BeforeSuite
    public void OpenBrowser(){

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //define the chrome options
        ChromeOptions options = new ChromeOptions();
        //define the arguments for options
        options.addArguments("start-maximize","incognito");
         driver = new ChromeDriver(options);
    }
    @AfterSuite
    public void closeBrowser(){
       // driver.quit();
    }
    @Test
    public void TestCase1(){
        System.out.println("Navigating to google home page");
        driver.navigate().to("https://www.google.com");
        //enter a keyword in google search
        sendKeysMethod(driver,"//*[@name='q']","Brooklyn", "Search field");
        submitMethod(driver,"//*[@value='Google Search']", "Google Search");
    }
@Test (dependsOnMethods = "TestCase1")
    public void TestCase2() {
    try{
        String searchresult = driver.findElement(By.xpath("//*[@id='resultStats']")).getText();
        String[] searchNumber = searchresult.split(" ");
        System.out.println("My Search number is " + searchNumber[1]);
    } catch(Exception err){
        System.out.println("unable to capture text for search result");

    }
}
    }
