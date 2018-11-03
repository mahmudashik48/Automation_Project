package ReusableObjects;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;

public class Reusable_method_Loggers {


        //method for navigating to a page
        public static void navigate(ExtentTest logger, WebDriver driver, String url) throws IOException {
            try {
               // System.out.print("Navigating to " + url);
                logger.log(LogStatus.INFO,"Navigating to " + url);
                driver.navigate().to(url);
            } catch (Exception err) {
               // System.out.println("Unable to navigate to the url... " + err);
                logger.log(LogStatus.FAIL, "Unable to navigate to the url" + err);
                getScreenshot(driver, logger, "URL Error");
            }
        }//end of navigate method

        //method for clicking on an element
        public static void clickMethod(ExtentTest logger,WebDriver driver, String locator, String elementName) throws IOException {

            try {
                System.out.println("Clicking on element " + elementName);
                logger.log(LogStatus.INFO, "Clicking on element " + elementName);
                //store the locator into WebElement variable
                WebElement clickbtn = driver.findElement(By.xpath(locator));
                clickbtn.click();
             //   logger.log(LogStatus.PASS, "Succesfully clicked on the element" + elementName);
            } catch (Exception err) {
                System.out.println("Unable to click on element " + elementName);
                logger.log(LogStatus.FAIL,"Succesfully clicked on the element" + elementName);
                getScreenshot(driver,logger,elementName);
            }//end of try catch
        }//end of click method

        //method for clearing on an element
        public static void clearMethod(ExtentTest logger,WebDriver driver, String locator, String elementName) throws IOException {
            try {
                System.out.println("Clearing on element " + elementName);
                logger.log(LogStatus.INFO, "Clearing element " + elementName);
                //store the locator into WebElement variable
                WebElement clrBtn = driver.findElement(By.xpath(locator));
                clrBtn.clear();
            } catch (Exception err) {
                System.out.println("Unable to clear on element " + elementName + " " + err);
                logger.log(LogStatus.FAIL,"Succesfully cleared element" + elementName);
                getScreenshot(driver,logger,elementName);
                
            }//end of try catch
        }//end of clear method

        //method for clicking on an element by index
        public static void clickMethodByIndex(ExtentTest logger, WebDriver driver, int indexNumber, String locator, String elementName) {

            try {
                System.out.println("Clicking on element " + elementName);
                //store the locator into WebElement variable
                WebElement clickbtn = driver.findElements(By.xpath(locator)).get(indexNumber);
                clickbtn.click();
            } catch (Exception err) {
                System.out.println("Unable to click on element " + elementName);
            }//end of try catch
        }//end of click by index method

        //method for submitting on an element
        public static void submitMethod(WebDriver driver, String locator, String elementName) {
            try {
                System.out.println("Submitting  on element " + elementName);
                //store the locator into WebElement variable
                WebElement submitBtn = driver.findElement(By.xpath(locator));
                submitBtn.submit();
            } catch (Exception err) {
                System.out.println("Unable to Submit on element " + elementName + " " + err);
            }//end of try catch
        }//end of submit method

        //method for entering on an element
        public static void sendKeysMethod(ExtentTest logger, WebDriver driver, String locator, String userInput, String elementName) throws IOException {
            try {
                System.out.println("Entering " + userInput + " in element " + elementName);
                logger.log(LogStatus.INFO, "Entering" + userInput + " in element " + elementName);
                //store the locator into WebElement variable
                WebElement input = driver.findElement(By.xpath(locator));
                input.sendKeys(userInput);
            } catch (Exception err) {
               // System.out.println("Unable to send info on element " + elementName);
                logger.log(LogStatus.FAIL, "unable to send info on element " + elementName);
                getScreenshot(driver,logger,elementName);

            }//end of try catch
        }//end of Send Keys method

        //dropdown method by visible text
        public static void selectByText(ExtentTest logger, WebDriver driver, String locator, String value, String elementName) throws IOException {
            try {
                System.out.println("Selecting " + value + " from dropdown " + elementName);
                logger.log(LogStatus.INFO,"Selecting" + value + " from dropdown" + elementName);
                //define the Web Element
                WebElement element = driver.findElement(By.xpath(locator));
                //define the select command
                Select select = new Select(element);
                // select by visible text
                select.selectByVisibleText(value);
            } catch (Exception err) {
                System.out.println("Unable to select a value from dropdown " + elementName + " " + err);
                logger.log(LogStatus.FAIL, "unable to select text " + elementName);
                getScreenshot(driver,logger,elementName);
            }//end of try catch
        }//end of select by text method

        //method for getText
        public static String captureText(ExtentTest logger, WebDriver driver, String locator, int indexNumber, String elementName) throws IOException {
            String textValue = null;
            try {
               // System.out.println("Capturing text " + elementName);
                textValue = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
                logger.log(LogStatus.PASS,"Capturing Text" + elementName);
            } catch (Exception err) {
               // System.out.println("Unable to capture text " + elementName + " " + err);
                logger.log(LogStatus.FAIL,"Unable to capture Text" + elementName);
                getScreenshot(driver,logger,elementName);
            }//end of try catch
            return textValue;
        }//end of capture text method

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {

        String path = "src\\main\\java\\Report_Folder\\Screenshots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);

    }//end of screenshot//


    public static void mousemovement(ExtentTest logger, WebDriver driver, String locator, String elementName) throws IOException {

        try{
            Actions mousemovement = new Actions(driver);
            logger.log(LogStatus.INFO,"Using mouse hover on element " + elementName);
            WebElement element = driver.findElement(By.xpath(locator));
            mousemovement.moveToElement(element).perform();
        }catch (Exception err) {
            logger.log(LogStatus.FAIL,"Unable to move to locator... " + err);
            getScreenshot(driver,logger,"Mouse Movement Error");
        }//end of try and catch

    }//end of mouse movement

    //method for comparing two data

    public static void compareMessages(ExtentTest logger, WebDriver driver, int indexNumber, String locator, String expectedMessage, String elementName) throws IOException {
            try{
                String ActualMessage = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
                if (expectedMessage.equalsIgnoreCase(ActualMessage)){
                    logger.log(LogStatus.PASS,"Expected matches with Actual for element " + elementName);
                }else{
                    logger.log(LogStatus.FAIL, "Expected - " + expectedMessage + "doesnt match with actual message");
                    getScreenshot(driver,logger,elementName);
                }
            }catch (Exception err){
                logger.log(LogStatus.FAIL, "Unable to locate element "+ elementName);
                getScreenshot(driver,logger,elementName);

            }//end of try and catch
    }//end of comparison method

   // }//end of main
}//end of parent

