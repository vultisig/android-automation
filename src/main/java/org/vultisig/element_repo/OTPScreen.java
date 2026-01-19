package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class OTPScreen {

    private final AppiumDriver driver;

    public OTPScreen(AppiumDriver driver) {
        this.driver = driver;
    }

    public void enterOtp(String otp) throws InterruptedException {
        if (otp == null || otp.length() != 4) {
            throw new IllegalArgumentException("OTP must be 4 digits");
        }

        System.out.println("🔢 Entering OTP: " + otp);

        WebElement otpBox = null;

        try {
            otpBox = driver.findElement(By.xpath("//android.widget.EditText"));
        } catch (Exception e1) {
            try {
                otpBox = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'')]"));
            } catch (Exception e2) {
                System.out.println("⚠️ OTP field not found with default locators.");
            }
        }

        if (otpBox == null) {
            throw new RuntimeException("OTP input field not found!");
        }

        otpBox.click();
        Thread.sleep(300);
        otpBox.clear();
        Thread.sleep(300);
        otpBox.sendKeys(otp);
        Thread.sleep(500);

        System.out.println("✅ OTP entry completed successfully!");
    }
}
