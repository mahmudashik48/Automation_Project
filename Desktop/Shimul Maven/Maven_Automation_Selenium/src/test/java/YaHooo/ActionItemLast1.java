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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionItemLast1 {

    /*Hi all i have attached class yahoo action item and reusable methods with loggers.
For your action item due by this coming Thursday evening follow the steps below:

First I want you to update your reusable methods class  that i attached such as for captureText, selectBytext, clear & mouseHover by incorporating LogStatus.INFO in try part and LogStatus.Fail in catch part with calling the screenshot Command in fail status...

you will be using testNG annotations for two separate test cases.... make sure you only use resuable methods for all user actions beside verifying the title of the page...

you will declare following global variables outside of your annotations
WebDriver driver; ExtentReports report; ExtentTest logger1; ExtentTest logger2
since we will have two test cases we will need two separate loggers
@Test case 1
you will define logger1 = report.startTest("Proceed to Check out for Tshirts"). you will use logger 1 for this
1. navigate to http://automationpractice.com/index.php
2. Verify the page title displays as My-Store //use if else with log.PASS and log.FAIL
3. using mouseHover method hover over Women tab
4., click on T-shirts link from there
5. scroll down about 350 times on next page
6. now hover over the picture with women in it
7. click on add to cart button
8. on the pop up using if else verify the message appears '
Product successfully added to your shopping cart
'
9. click on proceed to checkout button
10. change the quantity to 3 items
11. click on proceed to check out


now @Test case 2 will be depending on test case 1
you will start another logger saying
logger2 = report.startTest("Procedd to Checkout for Summer Dresses")
1. Hover over Dresses tab
2. click on Summer Dresses
3. scroll down about 250 to 300 times
4. hover over first picture of the dress
5. click on More tab
6. change the quantity to 4
5. select a size from dropdown(S,M or L)
6. click on 'Add to Car' button
7. on pop up verify checkpoint says Product successfully added to your shopping cart using if else condition with logger.pass and fail
8. click on proceed to checkout
9. next page click on delete icon to delete the item
10. on next page verify following message appears using if else
Your shopping cart is empty.

on your @AfterSuite annotation you will just call
report.EndTest(logger1);
report.EndTest(logger2);
report.flush;
report.close;
//driver.quit;

***Make sure you perform manual testing first before automating these scenarios*
good luck :) */

    WebDriver driver;
    ExtentReports report;
    ExtentTest logger1;
    ExtentTest logger2;



    @BeforeSuite
    public void openBrowser(){


        WebDriver driver;
        ExtentReports reports;
        ExtentTest logger1;
        ExtentTest logger2;

        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        //define the chrome options
        ChromeOptions options = new ChromeOptions();
        //define the arguments for options
        options.addArguments("start-maximize","incognito","disable-infobars");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(7,TimeUnit.SECONDS);
        // report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport" + UUID.randomUUID()+ ".html", true);
        report = new ExtentReports("src\\main\\java\\Report_Folder\\ExtentReport.html",true);

    }// End of before Suite

    @AfterSuite
    public void closeBrowser(){

        //end the test of the logger



        report.endTest(logger1);
        report.endTest(logger2);
        //flush the report
        report.flush();
        //close the report
        report.close();
        //driver.quit();


    }// end of afterSuite

    @Test
    public void ActionItemLast1Test1 () throws IOException, InterruptedException {
        //Starting the test
         logger1 = report.startTest("Proceed to Check out for Tshirts");
         //navigating to http://automationpractice.com/index.php (using reusable methods
        Reusable_method_Loggers.navigate(logger1,driver,"http://automationpractice.com/index.php");
        String Title = driver.getTitle();

        if (Title.equalsIgnoreCase("My-Store")){
            logger1.log(LogStatus.PASS, "The Automation title matches");
        }else{
            logger1.log(LogStatus.FAIL, "The Automation title Does not Match" + Title);
        }//end of if else

        //hovering
        Reusable_method_Loggers.mousemovement(logger1, driver, "//*[@title='Women']","Women's tab");
        Thread.sleep(1500);
        //clicking t shirts
        Reusable_method_Loggers.clickMethodByIndex(logger1,driver,0,"//*[@title='T-shirts']","T-shirt's tab");

        //scrolling
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,250");

        Thread.sleep(2000);
        Reusable_method_Loggers.mousemovement(logger1, driver,"//*[@title='Faded Short Sleeve T-shirts']", "Clicking on Picture");


        //adding to cart
        Reusable_method_Loggers.clickMethod(logger1,driver,"//*[@title='title=title='Add to cart']","Adding to cart");

        //verifying popup title
        String Titleofpopup = driver.findElement(By.xpath("//*[@class='icon-ok']")).getText();
        if (Titleofpopup.equalsIgnoreCase("Product successfully added to your shopping cart")){
            logger1.log(LogStatus.PASS,"The product was successfully added");
        }else {
            logger1.log(LogStatus.FAIL,"The item was not successfully added to cart");
        }//end of if else

        //checkout
        Reusable_method_Loggers.clickMethodByIndex(logger1,driver, 0,"//*[@title='Proceed to checkout']", "Proceed to checkout");
        //changing quantity
        Reusable_method_Loggers.clearMethod(logger1,driver,"//*[@title='Add']","Quantity");
        Reusable_method_Loggers.sendKeysMethod(logger1,driver,"//*[@class='cart_quantity_input from-control grey']","3","Adding 3");
        //checking out
        Reusable_method_Loggers.clickMethod(logger1,driver,"//*[@title='Proceed to checkout']", "Checking out");
    }//end of test suite

    @Test (dependsOnMethods = "ActionItemLast1Test1")
        public void ActionItemLast1Test2() throws IOException, InterruptedException {
        logger2 = report.startTest("Proceed to Checkout for Summer Dresses");
        Reusable_method_Loggers.mousemovement(logger2, driver, "//*[@title='Dresses']","Hovering on Dresses");
        Thread.sleep(2000);
        //clicking summer dress
        Reusable_method_Loggers.clickMethod(logger2,driver,"//*[@title='Summer Dresses']", "Summer Dress");
        Thread.sleep(2000);
        //scrolling
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("scroll(0,250");

        //Clicking on printed summer dress
        Reusable_method_Loggers.mousemovement(logger2, driver, "//*[@title='Printed Summer Dress']", "Printed Summer Dress");

        Reusable_method_Loggers.clickMethod(logger2, driver, "//*[@title='View']", "More Btn");
        Thread.sleep(2000);

        //Adding 4 dressed to cart

        Reusable_method_Loggers.clearMethod(logger1,driver,"//*[@title='Add']","Quantity");
        Thread.sleep(2000);
        //dropdown menu
        WebElement size = driver.findElement(By.xpath("//*[@name='group_1']"));
        Select sizeDropdown = new Select(size);
        //selecting size
        sizeDropdown.selectByVisibleText("S");
        Reusable_method_Loggers.clickMethod(logger2, driver, "//*[@name='Submit']", "Add to cart");

        //Verifying popup title
        String Titleofpopup = driver.findElement(By.xpath("//*[@class='icon-ok']")).getText();
        if (Titleofpopup.equalsIgnoreCase("Product successfully added to your shopping cart")){
            logger1.log(LogStatus.PASS,"The product was successfully added");
        }else {
            logger1.log(LogStatus.FAIL,"The item was not successfully added to cart");
        }//end of if else


        //checking out
        Reusable_method_Loggers.clickMethod(logger1, driver, "//*[@title='Proceed to checkout']", "Checkout");
        //clearing title
        Reusable_method_Loggers.clickMethod(logger1, driver, "//*[@title='Delete']", "Trash Icon");

        String Alert = driver.findElement(By.xpath("//*[@class='alert alert-warning")).getText();

        if (Alert.equalsIgnoreCase("Your shopping cart is empty.")){
            logger2.log(LogStatus.PASS, "Title matches");
        }else {
            logger2.log(LogStatus.FAIL, "Title doesn't match");
        }// end of if else



    }//end of test 2


}// End of main class
