package utils;

import io.appium.java_client.AppiumDriver;
import org.vultisig.element_repo.OTPScreen;

public class OtpRetryHandler {

    public static void enterOtpWithRetry(
            AppiumDriver driver,
            String userEmail,
            String appPassword,
            String sender,
            int maxAttempts
    ) throws Exception {

        OTPScreen otpScreen = new OTPScreen(driver);

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {

            System.out.println("🔁 OTP Attempt: " + attempt);

            String otp = GmailOtpFetcher.fetchOtp(
                    userEmail,
                    appPassword,
                    sender,
                    60
            );

            otpScreen.enterOtp(otp);

            boolean success = waitForOtpResult(otpScreen, 6);

            if (success) {
                System.out.println("✅ OTP verified successfully");
                return;
            }

            System.out.println("❌ OTP failed, retrying...");
        }

        throw new RuntimeException("❌ Otp is not working");
    }

    private static boolean waitForOtpResult(OTPScreen otpScreen, int timeoutSec)
            throws InterruptedException {

        for (int i = 0; i < timeoutSec * 2; i++) {

            // ✅ SUCCESS
            if (otpScreen.isNextScreenDisplayed()) {
                return true;
            }

            // ❌ FAILURE
            if (otpScreen.isOtpErrorDisplayed()) {
                return false;
            }

            Thread.sleep(500);
        }

        return false;
    }
}
