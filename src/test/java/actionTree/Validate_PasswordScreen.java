package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_PasswordScreen extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_PasswordScreen.class.getName());

    @Test(description = "Validate Password screen")
    public void Validate_PasswordScreen() throws InterruptedException {
        logger.info("🚀 Launching app and validating Welcome screen...");

        // ---------------------- Initialize Screens ----------------------
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());
        VultiServerPasswordScreen passwordScreen = new VultiServerPasswordScreen(DriverUtils.getDriver());
        OptionalHintScreen hintScreen = new OptionalHintScreen(DriverUtils.getDriver());

        // ---------------------- StartScreen ----------------------
        logger.info("🔹 Validating Welcome Screen...");
        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(), "❌ Vultisig logo should be visible");
        startScreen.clickCreateNewVaultButton();
        logger.info("✅ Clicked 'Create New Vault'");

        // ---------------------- OnboardingScreen ----------------------
        logger.info("🔹 Skipping Onboarding...");
        Assert.assertTrue(onboardingScreen2.isSkipButtonDisplayed(), "❌ Skip button should be visible");
        onboardingScreen2.clickSkipButton();
        logger.info("✅ Skip button clicked");

        // ---------------------- QuickSummaryScreen ----------------------
        logger.info("🔹 Quick Summary validation...");
        Assert.assertTrue(quickSummaryScreen.isReadAndUnderstandWhatToDoDisplayed(), "❌ Quick Summary text not displayed");
        quickSummaryScreen.clickCheckboxAndWaitForCreateVault();
        quickSummaryScreen.clickCreateVault();
        logger.info("✅ Quick Summary checkbox clicked and Create Vault pressed");

        // ---------------------- SetupScreen ----------------------
        logger.info("🔹 Setup screen validation...");
        setupScreen.clickFastVaultBox();
        Assert.assertTrue(setupScreen.isContinueButtonDisplayed(), "❌ Continue button should be visible");
        setupScreen.clickContinueButton();
        logger.info("✅ Fast Vault selected and Continue clicked");

        // ---------------------- VaultNameScreen ----------------------
        logger.info("🔹 Vault Name screen validation...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ 'Name your vault' title should be visible");
        vaultNameScreen.clickClearTextButton();
        vaultNameScreen.enterVaultName(DriverUtils.getDriver());
        vaultNameScreen.clickContinueButton();
        String vaultName = vaultNameScreen.getLastVaultName();
        logger.info("✅ Vault name entered: " + vaultName);

        // ---------------------- EmailScreen ----------------------
        logger.info("🔹 Entering email...");
        Assert.assertTrue(emailScreen.isEnterEmailDisplayed(), "❌ 'Enter your e-mail' title should be visible");
        emailScreen.enterEmail("brajaautomation@gmail.com");
        emailScreen.clickNextButton();
        logger.info("✅ Email entered and Next clicked");

        // ---------------------- VultiserverPasswordScreen ----------------------
        logger.info("🔹 Entering password...");
        Assert.assertTrue(passwordScreen.isPasswordTitleDisplayed(), "❌ 'Vultiserver Password' title should be visible");

        // ------------ Click back button ----------------------
        logger.info("🔹 Clicking Back button to validate navigation...");
        passwordScreen.clickBackButton();
        logger.info("✅ Back button clicked successfully");

        // ---------------------- EmailScreen (again) ----------------------
        logger.info("🔹 Verifying Email screen after back navigation...");
        Assert.assertTrue(emailScreen.isEnterEmailDisplayed(), "❌ 'Enter your e-mail' title should be visible");
        passwordScreen.clickNextButton();
        logger.info("✅ Navigated forward to Password screen");

        // ---------------------- VultiserverPasswordScreen ----------------------
        logger.info("🔹 Validating Password screen content...");
        Assert.assertTrue(passwordScreen.isPasswordTitleDisplayed(), "❌ 'Vultiserver Password' title should be visible");

        // Validate warning text
        logger.info("🔹 Checking password reset warning text...");
        Assert.assertTrue(
                passwordScreen.isPasswordResetWarningDisplayed(),
                "❌ Text 'Password cannot be reset or recovered' should be visible"
        );
        logger.info("✅ Password reset warning text is displayed successfully");

        // ---------------------- Next Button Disabled Validation ----------------------
        logger.info("🔹 Validating Next button is disabled by default...");
        Assert.assertFalse(
                passwordScreen.isNextButtonEnabled(),
                "❌ Next button should be disabled before entering password"
        );
        logger.info("✅ Next button is correctly disabled by default");

        // ---------------------- Password Entry & Validations ----------------------
        // Step 1: Enter password = "Test"
        logger.info("🔹 Entering password 'Test' in password field...");
        passwordScreen.enterPassword("Test");
        Thread.sleep(500);

        // Step 2: Enter incorrect confirm password
        logger.info("🔹 Entering non-matching confirm password 'Wrong'...");
        passwordScreen.confirmPassword("Wrong");
        Thread.sleep(700);

        // Step 3: Validate mismatch error
        logger.info("🔹 Validating 'Passwords do not match' error...");
        Assert.assertTrue(
                passwordScreen.isPasswordMismatchErrorDisplayed(),
                "❌ 'Passwords do not match' message should be displayed"
        );
        logger.info("✅ Mismatch validation passed");

        // Step 4: Enter correct confirm password = "Test"
        logger.info("🔹 Entering matching confirm password 'Test'...");
        passwordScreen.confirmPassword("Test");
        Thread.sleep(700);

        // Step 5: Toggle password eye icon 2 times
        logger.info("🔹 Toggling password visibility icon twice...");
        passwordScreen.togglePasswordEyeIcon(2);
        logger.info("👁️ Password eye icon clicked twice");

        // Step 6: Toggle confirm password eye icon 2 times
        logger.info("🔹 Toggling confirm-password visibility icon twice...");
        passwordScreen.toggleConfirmPasswordEyeIcon(2);
        logger.info("👁️ Confirm-password eye icon clicked twice");

        // Click Next to proceed
        passwordScreen.clickNextButton();
        logger.info("✅ Password entered, confirmed, and Next clicked");

        // ---------------------- OptionalHintScreen ----------------------
        logger.info("🔹 Validating Optional Hint screen...");
        Assert.assertTrue(hintScreen.isOptionalHintTitleDisplayed(), "❌ 'Add an optional hint' title should be visible");

        logger.info("✅ Test completed successfully");
    }
}
