package TestSuite;

import ReusableObjects.Reusable_method_Loggers;
import Utilities.AbstractClass_Parallel;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

import java.io.IOException;

public class Ecommerce_Test extends AbstractClass_Parallel {


    @Test
    public void TShirts () throws IOException, InterruptedException {

        loggers = report.startTest("Proceed to Check out for Tshirts");

        Reusable_method_Loggers.navigate(loggers, driver, "http://automationpractice.com/index.php");

        String title = driver.getTitle();

        if (title.equalsIgnoreCase("My-Store")) {

            loggers.log(LogStatus.PASS, "The title matches");

        } else {

            loggers.log(LogStatus.FAIL, "The title doesn't match " + title);

        }

        Reusable_method_Loggers.mousemovement(loggers, driver, "//*[@title='Women']", "Women Tab");

        Thread.sleep(2000);

        Reusable_method_Loggers.clickMethodByIndex(loggers ,driver, 0, "//*[@title='T-shirts']","T Shirt Link");

        //scrolling to the page
        jse.executeScript("scroll(0,350)");

        Thread.sleep(1000);

        Reusable_method_Loggers.mousemovement(loggers, driver, "//*[@title='Faded Short Sleeve T-shirts']", "Picture");

        Reusable_method_Loggers.clickMethod(loggers, driver,"//*[@title='Add to cart']", "Cart");

        Thread.sleep(1500);

        Reusable_method_Loggers.compareMessages(loggers ,driver,0,"//*[@id='layer_cart']/div[1]/div[1]/h2","Product successfully added to your shopping cart","Check point");

        Reusable_method_Loggers.clickMethod(loggers, driver, "//*[@title='Proceed to checkout']", "Checkout");

        Thread.sleep(1500);
        Reusable_method_Loggers.clearMethod(loggers, driver, "//*[@class='cart_quantity_input form-control grey']", "Quantity Field");

        Reusable_method_Loggers.sendKeysMethod(loggers, driver, "//*[@class='cart_quantity_input form-control grey']", "3","Increase Quantity Field");

        Thread.sleep(1500);
        Reusable_method_Loggers.clickMethod(loggers, driver, "//*[text()='Proceed to checkout']", "Checkout2");
    }
}
