package Utilities;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class AbstractClass_Parallel {

    public static WebDriver driver;
    public static ExtentReports report;
    public static ExtentTest loggers;
    public static JavascriptExecutor jse;



    @BeforeSuite
    public void openReport(){

        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);


    }//end of before suite

    @Parameters ("browser")
    @BeforeMethod
    public void loggerSession (String browser,Method methodName){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
       // System.setProperty("webdriver.ie.driver", "src\\main\\resources\\safari.exe");
         System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");

        //define the chrome options
       if (browser.equalsIgnoreCase("Chrome")) {
           ChromeOptions options = new ChromeOptions();
           //define the arguments for options
           options.addArguments("start-maximize", "incognito", "disable-infobars");
           driver = new ChromeDriver(options);
       }else if(browser.equalsIgnoreCase("firefox")){
           driver = new FirefoxDriver();
           driver.navigate().to("https://firefox.com");
           //driver.manage().window().fullscreen();

       }else if (browser.equalsIgnoreCase("ie")){
           //this is where your drive would go
       }else if(browser.equalsIgnoreCase("safari"));{
            //this is where your drive would go
        }
        driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        jse = (JavascriptExecutor)driver;
        loggers = report.startTest(methodName.getName());
        loggers.log(LogStatus.INFO,"Automation Test Scenario started ....");



    }

    @AfterSuite()
        public void closeBrowser(){
        report.flush();
        report.close();
        loggers.log(LogStatus.INFO,"Automation test suite ended");



    }//end of after site
@AfterMethod
        public void endTest(){

    report.endTest(loggers);

}



}//end of parent class
