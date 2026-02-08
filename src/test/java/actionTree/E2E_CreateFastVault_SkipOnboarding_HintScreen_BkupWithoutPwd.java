package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;
import utils.OtpRetryHandler;

import java.util.logging.Logger;

public class E2E_CreateFastVault_SkipOnboarding_HintScreen_BkupWithoutPwd extends BaseTest {

    private static final Logger logger = Logger.getLogger(
            E2E_CreateFastVault_SkipOnboarding_HintScreen_BkupWithoutPwd.class.getName()
    );

    @Test(description = "Complete E2E flow: Create Fast Vault → Skip Onboarding -> Hint → Backup Without Password → Validate Downloads → Validate Vaults")
    public void validateE2EFastVaultFlow() throws Exception {
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());
        VultiServerPasswordScreen passwordScreen = new VultiServerPasswordScreen(DriverUtils.getDriver());
        OptionalHintScreen optionalHintScreen = new OptionalHintScreen(DriverUtils.getDriver());
        VaultOverviewScreen overviewScreen = new VaultOverviewScreen(DriverUtils.getDriver());
        VaultBackupScreen vaultBackupScreen = new VaultBackupScreen(DriverUtils.getDriver());
        BackupChoiceScreen backupChoiceScreen = new BackupChoiceScreen(DriverUtils.getDriver());
        BackupPasswordScreen backupPasswordScreen = new BackupPasswordScreen(DriverUtils.getDriver());
        DownloadsPage downloadsPage = new DownloadsPage(DriverUtils.getDriver());
        BackupGuideScreen backupGuideScreen = new BackupGuideScreen(DriverUtils.getDriver());
        HomePage vaultsPage = new HomePage(DriverUtils.getDriver());

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
        String vaultName = VaultNameScreen.getLastVaultName();
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

        // ---------------------- OTP Screen ----------------------


        logger.info("🔹 Fetching OTP. ...");
        String userEmail = "brajaautomation@gmail.com";
        String appPassword = "msgh kcym rblv hmoc";
        String senderEmail = "vultisig";
        logger.info("🔹 Entering OTP with retry...");

        OtpRetryHandler.enterOtpWithRetry(
                DriverUtils.getDriver(),
                "brajaautomation@gmail.com",
                "dcef xkvk fgcn jsxx",
                "vultisig",
                3
        );

        logger.info("✅ OTP flow completed successfully");



        // ---------------------- VaultBackupScreen ----------------------
        logger.info("🔹 Validating Backup Vault screen...");
        Assert.assertTrue(vaultBackupScreen.isBackupVaultTitleDisplayed(), "❌ 'Backup Vault' title missing");
        vaultBackupScreen.clickNextButton();
        logger.info("✅ Backup Vault Next clicked");


        // ---------------------- DownloadsPage ----------------------
        logger.info("🔹 Validating Downloads Page...");
        //Assert.assertTrue(downloadsPage.isDownloadsPageDisplayed(DriverUtils.getDriver()), "❌ Downloads page not visible");
        Assert.assertTrue(downloadsPage.isVaultNamePresent(vaultName, DriverUtils.getDriver()), "❌ Vault name missing in file saving field");
        downloadsPage.clickSaveButton();
        logger.info("✅ Vault file saved successfully");

        // ---------------------- BackupGuideScreen ----------------------
        logger.info("🔹 Validating Backup Guide screen...");
        backupGuideScreen.clickCheckBox();
        backupGuideScreen.clickNextButton(DriverUtils.getDriver());
        logger.info("✅ Backup Guide checkbox clicked and Next pressed");

        // ---------------------- VaultsPage ----------------------
        logger.info("🔹 Validating Vaults Page...");
        Assert.assertTrue(vaultsPage.waitForWalletElement(DriverUtils.getDriver()), "❌ Wallet element not visible");
        vaultsPage.clickViewVaults();
        Assert.assertTrue(vaultsPage.isVaultNameDisplayed(vaultName, DriverUtils.getDriver()), "❌ Vault name not displayed in vault list");
        logger.info("✅ Vault name validated in Vaults page: " + vaultName);

        // ---------------------- Close App ----------------------
        DriverUtils.getDriver().quit();
        logger.info("✅ Application closed successfully");
    }
}
