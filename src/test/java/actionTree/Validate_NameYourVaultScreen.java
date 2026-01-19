package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_NameYourVaultScreen extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_NameYourVaultScreen.class.getName());

    @Test(description = "Validate the 'Name your vault' screen")
    public void Validate_NameYourVaultScreen() throws InterruptedException {
        logger.info("🚀 Launching app and validating Welcome screen...");

        // Initialize page objects (driver provided by BaseTest)
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());

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

        // ---------------------- VaultNameScreen (first visit) ----------------------
        logger.info("🔹 Vault Name screen validation...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ 'Name your vault' title should be visible");

        // Click back and verify navigation (as in original flow)
        vaultNameScreen.clickBackButton();
        logger.info("✅ Back button clicked successfully");

        // ---------------------- SetupScreen (again) ----------------------
        logger.info("🔹 Re-selecting Fast Vault and continuing...");
        setupScreen.clickFastVaultBox();
        Assert.assertTrue(setupScreen.isContinueButtonDisplayed(), "❌ Continue button should be visible");
        setupScreen.clickContinueButton();
        logger.info("✅ Fast Vault selected and Continue clicked");

        // ---------------------- VaultNameScreen (second visit) ----------------------
        logger.info("🔹 Vault Name screen validation (second visit)...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ 'Name your vault' title should be visible");

        // ---------------------- Info Text Validation ----------------------
        logger.info("🔹 Checking info text on Vault Name screen...");
        Assert.assertTrue(
                vaultNameScreen.isRenameVaultInfoTextDisplayed(),
                "❌ Info text 'You can always rename your vault later in the settings' should be visible"
        );
        logger.info("✅ Info text is visible");

        // Clear and validate Continue disabled by default
        vaultNameScreen.clickClearTextButton();
        logger.info("🔹 Cleared vault name text field");

        logger.info("🔹 Validating Continue button is disabled by default...");
        Assert.assertFalse(
                vaultNameScreen.isContinueButtonEnabled(),
                "❌ Continue button should be disabled before entering vault name"
        );
        logger.info("✅ Continue button is correctly disabled by default");

        // ---------------------- Enter the vault name ----------------------
        vaultNameScreen.enterVaultName(DriverUtils.getDriver());
        vaultNameScreen.clickContinueButton();
        String vaultName = vaultNameScreen.getLastVaultName();
        logger.info("✅ Vault name entered: " + vaultName);

        // ---------------------- EmailScreen ----------------------
        logger.info("🔹 Navigating to Email Screen...");
        Assert.assertTrue(emailScreen.isEnterEmailDisplayed(), "❌ 'Enter your e-mail' title should be visible");

        logger.info("✅ NameYourVaultScreen validation is successful");
    }
}
