package YaHooo;


import ReusableObjects.Reusable_method_Loggers;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Yahoo_searchResult {

    /*
    1) Navigate to Yahoo
    2) Verify the home page title as 'Yahoo'
    3) Verify the count of text links exist on home page
    4) Enter a keyword on search field
    5) Click on search
    6) Scroll to bottom for search result
    7) Capture search result
    8) Send it to extend report
    */
    //declare all the global variables before annotation methods

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger;

    @BeforeSuite
    public void openBrowser(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //define the chrome options
        ChromeOptions options = new ChromeOptions();
        //define the arguments for options
        options.addArguments("start-maximize","incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
       // report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+ ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);




    }//end of before suite


    @AfterSuite
    public void closerBrowser(){
        //end the test of the report
        report.endTest(logger);
        //flush the report
        report.flush();
        //close the report
        report.close();
        //driver.quit();

    }// end of after suite

    @Test
    public void Yahoo_searchResult() throws InterruptedException, IOException {
        //Start the test
        logger = report.startTest("Yahoo Search Result");
        //navigate to yahoo
        Reusable_method_Loggers.navigate(logger,driver,"https://www.yahoo.com");
        //verify the home page
        String yahooTitle = driver.getTitle();
        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            logger.log(LogStatus.PASS, "The yahoo title matches");
        }else {
            logger.log(LogStatus.FAIL, "The Yahoo title doesn't match" + yahooTitle);
        }

        //verify the list count
        List <WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        logger.log(LogStatus.INFO, "The Link count is " + linkCount.size());

        //enter a keyword on a search field
        Reusable_method_Loggers.sendKeysMethod(logger, driver, "//*[@name='p']" ,"Brooklyn", "Search Field");

        //click on search icon
        Reusable_method_Loggers.clickMethod(logger, driver, "//*[@id= 'uhoo-search-button']" , "Search Icon");

        //define javascript executor
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        //scroll to the bottom of the page
        logger.log(LogStatus.INFO, "Scrolling to the bottom of the search result page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,2000)");



    }//end of test

}// end of parent class
