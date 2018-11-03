package ReusableObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Reusable_methods {


        //method ro navigating to a page
        public static void navigate(WebDriver driver, String url) {
            try {
                System.out.print("Navigating to " + url);
                driver.navigate().to(url);
            } catch (Exception err) {
                System.out.println("Unable to navigate to the url... " + err);
            }
        }//end of navigate method

        //method for clicking on an element
        public static void clickMethod(WebDriver driver, String locator, String elementName) {

            try {
                System.out.println("Clicking on element " + elementName);
                //store the locator into WebElement variable
                WebElement clickbtn = driver.findElement(By.xpath(locator));
                clickbtn.click();
            } catch (Exception err) {
                System.out.println("Unable to click on element " + elementName);
            }//end of try catch
        }//end of click method

        //method for clearing on an element
        public static void clearMethod(WebDriver driver, String locator, String elementName) {
            try {
                System.out.println("Clearing on element " + elementName);
                //store the locator into WebElement variable
                WebElement clrBtn = driver.findElement(By.xpath(locator));
                clrBtn.clear();
            } catch (Exception err) {
                System.out.println("Unable to clear on element " + elementName + " " + err);
            }//end of try catch
        }//end of clear method

        //method for clicking on an element by index
        public static void clickMethodByIndex(WebDriver driver, String locator, int indexNumber, String elementName) {

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
        public static void sendKeysMethod(WebDriver driver, String locator, String userInput, String elementName) {
            try {
                System.out.println("Entering " + userInput + " in element " + elementName);
                //store the locator into WebElement variable
                WebElement input = driver.findElement(By.xpath(locator));
                input.sendKeys(userInput);
            } catch (Exception err) {
                System.out.println("Unable to send info on element " + elementName);
            }//end of try catch
        }//end of Send Keys method

        //dropdown method by visible text
        public static void selectByText(WebDriver driver, String locator, String value, String elementName) {
            try {
                System.out.println("Selecting " + value + " from dropdown " + elementName);
                //define the Web Element
                WebElement element = driver.findElement(By.xpath(locator));
                //define the select command
                Select select = new Select(element);
                // select by visible text
                select.selectByVisibleText(value);
            } catch (Exception err) {
                System.out.println("Unable to select a value from dropdown " + elementName + " " + err);
            }//end of try catch
        }//end of select by text method

        //method for getText
        public static String captureText(WebDriver driver, String locator, int indexNumber, String elementName) {
            String textValue = null;
            try {
                System.out.println("Capturing text " + elementName);
                textValue = driver.findElements(By.xpath(locator)).get(indexNumber).getText();
            } catch (Exception err) {
                System.out.println("Unable to capture text " + elementName + " " + err);
            }//end of try catch
            return textValue;
        }//end of capture text method

   // }//end of main
}//end of parent
