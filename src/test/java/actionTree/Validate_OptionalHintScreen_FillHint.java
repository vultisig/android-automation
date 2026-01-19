package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_OptionalHintScreen_FillHint extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_OptionalHintScreen_FillHint.class.getName());

    @Test(description = "Validate Optional Hint screen by filling the Hint")
    public void Validate_OptionalHintScreen() throws InterruptedException {
        logger.info("🚀 Launching app and validating Welcome screen...");

        // Initialize page objects (driver provided by BaseTest)
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());
        VultiServerPasswordScreen passwordScreen = new VultiServerPasswordScreen(DriverUtils.getDriver());
        OptionalHintScreen optionalHintScreen = new OptionalHintScreen(DriverUtils.getDriver());
        VaultOverviewScreen overviewScreen = new VaultOverviewScreen(DriverUtils.getDriver());

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
        passwordScreen.enterPassword("Test@123");
        passwordScreen.confirmPassword("Test@123");
        passwordScreen.clickNextButton();
        logger.info("✅ Password entered, confirmed, and Next clicked");

        // ---------------------- OptionalHintScreen ----------------------
        logger.info("🔹 Optional Hint screen validation...");
        Assert.assertTrue(optionalHintScreen.isOptionalHintTitleDisplayed(), "❌ 'Add an optional hint' title should be visible");

        logger.info("🔹 Validating 'forget password hint' text...");
        Assert.assertTrue(
                optionalHintScreen.isForgetPasswordHintDisplayed(),
                "❌ Text 'This will be shown in case you forget your password' should be visible"
        );
        logger.info("✅ Forget password hint text is displayed successfully");

        // ---------------------- VaultNameScreen (navigate back) ----------------------
        logger.info("🔹 Navigating back to Password screen and forward again...");
        optionalHintScreen.clickBackButton();
        logger.info("✅ Back button clicked successfully");

        passwordScreen.clickNextButton();
        logger.info("✅ Returned to Optional Hint screen via Next");

        logger.info("🔹 Confirming Optional Hint title again...");
        Assert.assertTrue(optionalHintScreen.isOptionalHintTitleDisplayed(), "❌ 'Add an optional hint' title should be visible");

        // ---------------------- Next Button Disabled Validation ----------------------
        logger.info("🔹 Validating Next button is disabled by default...");
        Assert.assertFalse(
                optionalHintScreen.isNextButtonEnabled(),
                "❌ Next button should be disabled before entering hint"
        );
        logger.info("✅ Next button is correctly disabled by default");

        // ---------------------- Enter hint text ----------------------
        logger.info("🔹 Entering hint text 'Test@123'...");
        optionalHintScreen.enterHintText();
        logger.info("✅ Hint text entered successfully");

        // ---------------------- Click Next button ----------------------
        logger.info("🔹 Clicking Next button...");
        optionalHintScreen.clickNextButton();
        logger.info("✅ Next button clicked successfully");

        // ---------------------- VaultOverviewScreen ----------------------
        logger.info("🔹 Validating Vault Overview...");
        Assert.assertTrue(overviewScreen.isVaultOverviewTitleDisplayed(), "❌ Vault Overview title missing");
        Assert.assertTrue(overviewScreen.isBackupInfoDisplayed(), "❌ Backup info missing");

        overviewScreen.clickBackupButton();
        Assert.assertTrue(overviewScreen.isServerShareTextDisplayed(), "❌ Server share info missing");

        overviewScreen.clickServerShareNextButton();
        Assert.assertTrue(overviewScreen.isSelfCustodyTextDisplayed(), "❌ Self-custody info missing");

        overviewScreen.clickSelfCustodyNextButton();
        logger.info("✅ Vault Overview validated and navigated through backup info");

        Thread.sleep(3000);
        logger.info("🎉 Optional hint flow completed successfully");
    }
}
