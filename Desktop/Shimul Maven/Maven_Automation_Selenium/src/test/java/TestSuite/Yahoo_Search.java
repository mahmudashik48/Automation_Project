package TestSuite;

import ReusableObjects.Reusable_method_Loggers;
import Utilities.AbstractClass_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class Yahoo_Search extends AbstractClass_Parallel {
    @Test
    public void Yahoo_searchResult() throws InterruptedException, IOException {
        //Start the test
        loggers = report.startTest("Yahoo Search Result");
        //navigate to yahoo
        Reusable_method_Loggers.navigate(loggers,driver,"https://www.yahoo.com");
        //verify the home page
        String yahooTitle = driver.getTitle();
        if(yahooTitle.equalsIgnoreCase("Yahoo")){
            loggers.log(LogStatus.PASS, "The yahoo title matches");
        }else {
            loggers.log(LogStatus.FAIL, "The Yahoo title doesn't match" + yahooTitle);
        }

        //verify the list count
        List<WebElement> linkCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(')]"));
        loggers.log(LogStatus.INFO, "The Link count is " + linkCount.size());

        //enter a keyword on a search field
        Reusable_method_Loggers.sendKeysMethod(loggers, driver, "//*[@name='p']" ,"Brooklyn", "Search Field");

        //click on search icon
        Reusable_method_Loggers.clickMethod(loggers, driver, "//*[@id= 'uhoo-search-button']" , "Search Icon");

        //define javascript executor
        JavascriptExecutor jse = (JavascriptExecutor)driver;

        //scroll to the bottom of the page
        loggers.log(LogStatus.INFO, "Scrolling to the bottom of the search result page");
        Thread.sleep(1500);
        jse.executeScript("scroll(0,2000)");



    }//end of test
    }




