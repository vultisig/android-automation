package actionTree;

import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import org.vultisig.element_repo.StartScreen;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_ChooseSetup_Screen extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_ChooseSetup_Screen.class.getName());

    @Test(description = "Validate the Choose Setup screen ")
    public void validateImportVaultButton() throws InterruptedException {

        logger.info("🚀 Starting Choose Setup Test...");

        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());

        // START SCREEN
        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Start Screen");
        startScreen.assertVultisigName();
        logger.info("Checking OR Text is displayed...");
        startScreen.isORDisplayed();
        startScreen.clickCreateNewVaultButton();
        logger.info("✅ Clicked 'Create New Vault'");

        // ---------------------- OnboardingScreen ----------------------
        logger.info("🔹 Skipping Onboarding...");

        Assert.assertTrue(onboardingScreen2.isVaultShareTextDisplayed(),
                "❌ 'Say hello to vault shares...' text should be displayed");
        logger.info("✅ Vault Share intro text is displayed");

        onboardingScreen2.clickBackButton();
        logger.info("✅ Back button clicked successfully");

        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Start Screen");
        startScreen.assertVultisigName();
        startScreen.clickCreateNewVaultButton();

        Assert.assertTrue(onboardingScreen2.isSkipButtonDisplayed(), "❌ Skip button should be visible");
        onboardingScreen2.clickSkipButton();
        logger.info("✅ Skip button clicked");

        // ---------------------- QuickSummaryScreen ----------------------
        logger.info("🔹 Quick Summary validation...");

        Assert.assertTrue(quickSummaryScreen.isReadAndUnderstandWhatToDoDisplayed(),
                "❌ Quick Summary text not displayed");
        quickSummaryScreen.validateDefaultStates();
        quickSummaryScreen.clickCheckboxAndWaitForCreateVault();
        quickSummaryScreen.clickCreateVault();
        logger.info("✅ Quick summary screen validation done ");

        // ------------------------ Validate Choose setup screen --------------------
        logger.info("🔙 Clicking back button on Choose Setup screen...");
        setupScreen.clickBackButtonOnChooseSetup();
        logger.info("✅ Back button clicked successfully");

        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Start Screen");
        startScreen.assertVultisigName();
        logger.info("Checking OR Text is displayed...");
        startScreen.isORDisplayed();

        // Return back to setup screen
        logger.info("Returning to choose setup screen");

        // --------------- Create vault ------------
        startScreen.clickCreateNewVaultButton();
        logger.info("Create vault button clicked successfully");

        // Validate -----------Information icon ----
        boolean ok = setupScreen.clickInfoAndReturnSimple();
        Assert.assertTrue(ok, "Info icon flow failed to return to Choose Setup screen");
        logger.info("✅ Info link validated — navigation works perfectly");

        // validate -------- The the vault type and click on next----
        logger.info("🔹 Setup screen validation...");
        Thread.sleep(5000);

        // Assert-style validations:
        setupScreen.validateFastVaultTextsAssert();
        setupScreen.clickSecureVaultByXpath();
        setupScreen.validateUltimateSecurityTextsAssert();

        Thread.sleep(3000);
        setupScreen.clickFastVaultBox();
        Thread.sleep(2000);

        Assert.assertTrue(setupScreen.isContinueButtonDisplayed(), "❌ Continue button should be visible");
        setupScreen.clickContinueButton();
        logger.info("✅ Fast Vault selected and Continue clicked");

        // ---------------------- VaultNameScreen ----------------------
        logger.info("🔹 Vault Name screen validation...");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(),
                "❌ 'Name your vault' title should be visible");

        DriverUtils.getDriver().quit();
    }
}
