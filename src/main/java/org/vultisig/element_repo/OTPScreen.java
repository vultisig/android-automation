package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class OTPScreen {

    private final AppiumDriver driver;

    private final By otpInput = By.xpath("//android.widget.EditText");

    // ❌ OTP error
    private final By otpErrorMessage =
            By.xpath("//android.widget.TextView[@text='Error verifying code, please re-try']");

    // ✅ NEXT SCREEN IDENTIFIER (CHANGE IF NEEDED)
    private final By nextScreenIdentifier =
            By.xpath("//android.widget.TextView[contains(@text,'Backup')]");

    public OTPScreen(AppiumDriver driver) {
        this.driver = driver;
    }

    public void enterOtp(String otp) throws InterruptedException {
        WebElement otpBox = driver.findElement(otpInput);
        otpBox.click();
        Thread.sleep(300);
        otpBox.clear();
        Thread.sleep(300);
        otpBox.sendKeys(otp);
        Thread.sleep(500);
    }

    public boolean isOtpErrorDisplayed() {
        try {
            return driver.findElement(otpErrorMessage).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isNextScreenDisplayed() {
        try {
            return driver.findElement(nextScreenIdentifier).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
