package Google;

import ReusableObjects.Reusable_method_Loggers;
import Utilities.AbstractClass;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class CrossBrowserTestAItem extends AbstractClass {

    @Test
    public void Action_Item3 () throws IOException, InterruptedException {

        Reusable_method_Loggers.navigate(logger, driver, "https://www.yahoo.com");

        String title = driver.getTitle();

        if (title.equals("Yahoo")) {

            logger.log(LogStatus.PASS, "Title matches");

        } else {

            logger.log(LogStatus.FAIL, "The title doesn't match " + title);

        }

        List<WebElement> Elements = driver.findElements(By.xpath("//*[contains(@class,'D(ib) Mstart(21px) Mend(13px)')]"));
        logger.log(LogStatus.INFO,"link count is "+ Elements.size());

        System.out.println("Number of elements " + Elements.size());



       Reusable_method_Loggers.sendKeysMethod(logger, driver, "//*[@name='p']", "Nutrition", "Search Bar");

        Thread.sleep(1500);

        Reusable_method_Loggers.clickMethod(logger, driver, "//*[@id='uh-search-button']", "Yahoo Search");
        Thread.sleep(1500);

//        jse.executeScript("scroll(0,5000");

        String searchresult = Reusable_method_Loggers.captureText(logger,driver,"//*[@class='compPagination",0,"search result");
       // String[] arraySearch = searchresult.split("Next");
       // logger.log(LogStatus.INFO,"search count is"+arraySearch[1].trim());
        // Thread.sleep(1500);

        Thread.sleep(1500);
//        jse.executeScript("scroll(-5000,0");
        Reusable_method_Loggers.clickMethod(logger, driver, "//*[@id='yucs-login_signIn']", "Sign In");
        Thread.sleep(2000);
        boolean checkBoxState = driver.findElement(By.xpath("//*[@id='persistent']")).isSelected();


        if(checkBoxState == true){
            logger.log(LogStatus.PASS,"Checkbox is selected by default");
        }else {
            logger.log(LogStatus.FAIL,"CheckBox is not selected by defult");
            Reusable_method_Loggers.getScreenshot(driver,logger,"check Box state");
        }

        Reusable_method_Loggers.sendKeysMethod(logger, driver, "//*[@id='login-username']", "FalseEMAIL", "Username");
        Thread.sleep(1500);
        Reusable_method_Loggers.clickMethod(logger, driver, "//*[@id='login-signin']", "Next Button");
        Thread.sleep(1500);
        Reusable_method_Loggers.compareMessages(logger, driver, 0,"Email not recognized", "//*[@class='row error']", "Error Message");


    }






}//end of parent class





/*Use Abstract_Class_Parallel execute this scenario in chrome & firefox…

Use only reusable methods unless you need to use additional conditions



Test Steps:

Step 1. Navigate to https://www.yahoo.com

Step 2. Assert that we are on the correct page by checking the title = 'Yahoo‘   //use if else with logger

Step 3. Display the count of options on the left side panel ('Mail', 'News', 'Sports‘ & ‘More Yahoo Sites’) //use list<WebElement> and print out the count in logger

Step 4: Enter 'Nutrition' on the search bar on the top

Step 5: Click on ‘Search’ button

Step 6. Scroll down to the page // javascript executor and go all the bottom of the page let say 5000

Step 7: Display the search result Number

Step 8: Click on Sign In button

Step 9: Verify the Boolean state of checkbox is selected as default //use boolen to check if check box is selected true/false

Syntax:

Boolean elementState = driver.findElements(By.xpath(“”)); //inspect the check mark and put the property in xpath

If(elementState == true){

    Logger.log(LogStatus.PASS,”Element is checked by default”);

else{

    Logger.log(LogStatus.FAIL,”Element is not checked by default”);

   getScreenshot(driver,logger,”Check Mark”); //call the get screen shot command

}

Step 10: Enter invalid user name

Step 11: click on ‘Next’ button

Step 12: Capture the error message and verify that if message matches the following string

               String errMsg = "Sorry, we don't recognize this email.";

 */