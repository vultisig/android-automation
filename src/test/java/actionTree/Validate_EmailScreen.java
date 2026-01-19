package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_EmailScreen extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_EmailScreen.class.getName());

    @Test(description = "Validate Email Screen")
    public void Validate_EmailScreen() throws InterruptedException {

        logger.info("🚀 Launching app and validating Welcome screen...");

        // Initialize page objects
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());
        VultiServerPasswordScreen passwordScreen = new VultiServerPasswordScreen(DriverUtils.getDriver());


        // -----------------------------------------------------------
        // START SCREEN VALIDATION
        // -----------------------------------------------------------
        logger.info("🔹 Validating Welcome Screen...");
        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(), "❌ Vultisig logo should be visible");

        startScreen.clickCreateNewVaultButton();
        logger.info("✅ Clicked 'Create New Vault'");


        // -----------------------------------------------------------
        // ONBOARDING SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Skipping Onboarding...");
        Assert.assertTrue(onboardingScreen2.isSkipButtonDisplayed(), "❌ Skip button should be visible");

        onboardingScreen2.clickSkipButton();
        logger.info("✅ Skip button clicked");


        // -----------------------------------------------------------
        // QUICK SUMMARY SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Quick Summary validation...");
        Assert.assertTrue(quickSummaryScreen.isReadAndUnderstandWhatToDoDisplayed(), "❌ Quick Summary text not displayed");

        quickSummaryScreen.clickCheckboxAndWaitForCreateVault();
        quickSummaryScreen.clickCreateVault();
        logger.info("✅ Quick Summary checkbox clicked and Create Vault pressed");


        // -----------------------------------------------------------
        // SETUP SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Setup screen validation...");
        setupScreen.clickFastVaultBox();

        Assert.assertTrue(setupScreen.isContinueButtonDisplayed(), "❌ Continue button should be visible");

        setupScreen.clickContinueButton();
        logger.info("✅ Fast Vault selected and Continue clicked");


        // -----------------------------------------------------------
        // VAULT NAME SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Vault Name screen validation...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ 'Name your vault' title should be visible");

        vaultNameScreen.clickClearTextButton();
        vaultNameScreen.enterVaultName(DriverUtils.getDriver());
        vaultNameScreen.clickContinueButton();

        String vaultName = VaultNameScreen.getLastVaultName();
        logger.info("✅ Vault name entered: " + vaultName);


        // -----------------------------------------------------------
        // EMAIL SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Entering email...");
        Assert.assertTrue(emailScreen.isEnterEmailDisplayed(), "❌ 'Enter your e-mail' title should be visible");

        emailScreen.clickBackButton();
        logger.info("✅ Back button clicked successfully");


        // -----------------------------------------------------------
        // RETURN TO VAULT NAME SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Vault Name screen validation after returning...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ 'Name your vault' title should be visible");

        vaultNameScreen.clickContinueButton();


        // -----------------------------------------------------------
        // SERVER BACKUP INFO TEXT
        // -----------------------------------------------------------
        logger.info("🔹 Checking server backup info text...");

        Assert.assertTrue(
                emailScreen.isServerBackupInfoTextDisplayed(),
                "❌ Text 'This email is only used to send the server backup' should be visible"
        );

        logger.info("✅ Server backup info text is displayed successfully");


        // -----------------------------------------------------------
        // NEXT BUTTON DISABLED VALIDATION
        // -----------------------------------------------------------
        logger.info("🔹 Validating Next button is disabled by default...");

        Assert.assertFalse(
                emailScreen.isNextButtonEnabled(),
                "❌ Next button should be disabled before entering email"
        );

        logger.info("✅ Next button is correctly disabled by default");


        // -----------------------------------------------------------
        // INVALID EMAIL VALIDATION
        // -----------------------------------------------------------
        logger.info("🔹 Starting invalid email format tests...");

        String[] invalidEmails = new String[] {
                "a1b2.c3",      // missing @
                "a1@b2c3",      // missing dot
                "ab@c3",        // wrong local part format
                "11@b2.",       // starts with digit
                "a1@@b2.c3",    // double @
                "a1@b2..c3",    // double dot
                "a1@"           // incomplete
        };

        for (String invalid : invalidEmails) {

            logger.info("🔹 Testing invalid email: '" + invalid + "'");

            emailScreen.clearEmailField();
            emailScreen.enterEmailScenario(invalid);

            Assert.assertTrue(
                    emailScreen.isIncorrectEmailMessageVisible(),
                    "❌ Error message should be visible for invalid email: " + invalid
            );
        }

        logger.info("✅ All invalid email scenarios validated successfully");


        // -----------------------------------------------------------
        // VALID EMAIL SCENARIO
        // -----------------------------------------------------------
        String validEmail = "brajaautomation@gmail.com";

        logger.info("🔹 Testing valid email: '" + validEmail + "'");

        emailScreen.clearEmailField();
        emailScreen.enterEmailScenario(validEmail);

        logger.info("✅ Valid email entered and accepted");

        Thread.sleep(2000);
        emailScreen.clickNextButton();

        logger.info("✅ Valid email submitted successfully");


        // -----------------------------------------------------------
        // PASSWORD SCREEN
        // -----------------------------------------------------------
        logger.info("🔹 Validating Password Screen...");

        Assert.assertTrue(passwordScreen.isPasswordTitleDisplayed(), "❌ 'Vultiserver Password' title should be visible");

        logger.info("🎉 Email screen validated successfully!");
    }
}
