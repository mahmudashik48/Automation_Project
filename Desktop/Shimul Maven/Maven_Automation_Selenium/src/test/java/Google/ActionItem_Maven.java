package Google;

import ReusableObjects.Reusable_methods;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ActionItem_Maven {

    //global variables shared across methods
    WebDriver driver;
    //declare all readable & writable excel workbook and worksheet here since it's global
    Workbook readable;
    Sheet readableSheet;
    WritableWorkbook writable;
    WritableSheet WritableSheet;
    int rows;

    SoftAssert SoftAssert = new SoftAssert();


    @BeforeSuite
    public void OpenBrowser() throws IOException, BiffException {
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximize", "incognito");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

        //defining excel readable pat

        readable = Workbook.getWorkbook(new File("src\\main\\resources\\AutoAAmaven.xls"));
        readableSheet = readable.getSheet(0);

        writable = Workbook.createWorkbook(new File("src\\main\\resources\\AutoAAmaven.xls"), readable);
        WritableSheet = writable.getSheet(0);
        rows = readableSheet.getRows();


    }//end of before suite

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writable.write();
        writable.close();
        readable.close();
        driver.quit();


    }//end of after suite

    @Test
//where test takes place (execution); call on write
    public void testScenario() throws WriteException {

        for (int i = 1; i < rows; i++) {

            String PurchasePrice = readableSheet.getCell(0, i).getContents();
            String DownPayment = readableSheet.getCell(1, i).getContents();
            String InterestRate = readableSheet.getCell(2, i).getContents();
            String ZipCode = readableSheet.getCell(3, i).getContents();
            String PaymentMonth = readableSheet.getCell(4, i).getContents();
            String PaymentYear = readableSheet.getCell(5, i).getContents();

            Reusable_methods.navigate(driver, "https://www.mlcalc.com");


            //verify the expected title using Hard Assert
            //Assert.assertEquals("morgage calc", driver.getTitle());
            //verify the expected title using soft assert
            SoftAssert.assertEquals("Mortgage Loan Calculator", driver.getTitle());


            //clear purchase field
            Reusable_methods.clearMethod(driver, "//*[@name='ma']", "Purchase Price");
            //enter information to purchase price
            Reusable_methods.sendKeysMethod(driver,"//*[@name='dp']", PurchasePrice, "purchase Price");

            //Clear down payment field
            Reusable_methods.clearMethod(driver,"//*[@name='dp'", "down Payment");
            //Entering information onto the purchase payment
            Reusable_methods.sendKeysMethod(driver, "//*[@name=dp']", DownPayment,"Down payment");

            //clear Interest Rate field
            Reusable_methods.clearMethod(driver,"//*[@name='ir']","Interest Rate");
            //enter information to purchase price
            Reusable_methods.sendKeysMethod(driver, "//*[@name='ir']", InterestRate , "Interest Rate");

            //clear zip code field
            Reusable_methods.clearMethod(driver, "//*[@name='zipCode]","Zip Code");
            //Enter information to purchase price
            Reusable_methods.sendKeysMethod(driver,"//*[@name='zipCode']", ZipCode, "Zip Code");

            //select pay month
            Reusable_methods.selectByText(driver,"//*[@name='sm']", PaymentMonth, "month");

            //Select pay year
            Reusable_methods.selectByText(driver,"//*[@name='sy']", PaymentYear, "year");

            //Click on calculate

            System.out.println("Navigating to mcalc home page");
            driver.navigate().to("https://www.mlcalc.com");
        } //end of loop
    //AssertAll using soft assert will handle and catch your exception show it on your logs
      //  softAssert.assertAll();
    }//end of test execution

}

