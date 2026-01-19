package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import io.appium.java_client.AppiumDriver;

public class OtpScreenHelper {

    // Enter OTP into 4 separate fields
    public static void enterOtpDigitFields(AppiumDriver driver, String otp) throws InterruptedException {
        for (int i = 0; i < 4; i++) {
            WebElement box = driver.findElement(By.xpath("(//android.widget.EditText)[" + (i + 1) + "]"));
            box.click();
            Thread.sleep(150);
            box.clear();
            Thread.sleep(100);
            box.sendKeys(String.valueOf(otp.charAt(i)));
            Thread.sleep(150);
        }
    }
}
