package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;
import utils.OtpRetryHandler;

import java.util.logging.Logger;

public class E2E_CreateFastVault_ValidateSettings extends BaseTest {

    private static final Logger logger =
            Logger.getLogger(E2E_CreateFastVault_ValidateSettings.class.getName());

    @Test(
            description = "Complete E2E flow: Create Fast Vault → Validate Settings → Vault Settings → Advanced → Delete"
    )
    public void Validate_Settings() throws Exception {

        logger.info("🚀 Launching app and starting E2E flow");

        // ==================== Initialize Screens ====================

        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        QuickSummaryScreen quickSummaryScreen = new QuickSummaryScreen(DriverUtils.getDriver());
        SetupScreen setupScreen = new SetupScreen(DriverUtils.getDriver());
        VaultNameScreen vaultNameScreen = new VaultNameScreen(DriverUtils.getDriver());
        EmailScreen emailScreen = new EmailScreen(DriverUtils.getDriver());
        VultiServerPasswordScreen passwordScreen = new VultiServerPasswordScreen(DriverUtils.getDriver());
        OptionalHintScreen hintScreen = new OptionalHintScreen(DriverUtils.getDriver());
        VaultOverviewScreen overviewScreen = new VaultOverviewScreen(DriverUtils.getDriver());
        VaultBackupScreen vaultBackupScreen = new VaultBackupScreen(DriverUtils.getDriver());
        DownloadsPage downloadsPage = new DownloadsPage(DriverUtils.getDriver());
        BackupGuideScreen backupGuideScreen = new BackupGuideScreen(DriverUtils.getDriver());
        HomePage vaultsPage = new HomePage(DriverUtils.getDriver());
        SettingsScreen settingsScreen = new SettingsScreen(DriverUtils.getDriver());

        // ==================== Start Screen ====================

        logger.info("🔹 Validating Welcome screen");
        Assert.assertTrue(startScreen.isVultisigLogoDisplayed(), "❌ Vultisig logo should be visible");
        startScreen.clickCreateNewVaultButton();
        logger.info("✅ Create New Vault clicked");

        // ==================== Onboarding ====================

        logger.info("🔹 Skipping onboarding");
        Assert.assertTrue(onboardingScreen2.isSkipButtonDisplayed(), "❌ Skip button missing");
        onboardingScreen2.clickSkipButton();
        logger.info("✅ Onboarding skipped");

        // ==================== Quick Summary ====================

        logger.info("🔹 Validating Quick Summary");
        Assert.assertTrue(
                quickSummaryScreen.isReadAndUnderstandWhatToDoDisplayed(),
                "❌ Quick Summary text missing"
        );
        quickSummaryScreen.clickCheckboxAndWaitForCreateVault();
        quickSummaryScreen.clickCreateVault();
        logger.info("✅ Quick Summary completed");

        // ==================== Setup ====================

        logger.info("🔹 Selecting Fast Vault");
        setupScreen.clickFastVaultBox();
        Assert.assertTrue(setupScreen.isContinueButtonDisplayed(), "❌ Continue button missing");
        setupScreen.clickContinueButton();
        logger.info("✅ Fast Vault selected");

        // ==================== Vault Name ====================

        logger.info("🔹 Entering vault name");
        Assert.assertTrue(vaultNameScreen.isNameYourVaultDisplayed(), "❌ Name Your Vault title missing");
        vaultNameScreen.clickClearTextButton();
        vaultNameScreen.enterVaultName(DriverUtils.getDriver());
        vaultNameScreen.clickContinueButton();

        String vaultName = vaultNameScreen.getLastVaultName();
        logger.info("✅ Vault name created: " + vaultName);

        // ==================== Email ====================

        logger.info("🔹 Entering email");
        Assert.assertTrue(emailScreen.isEnterEmailDisplayed(), "❌ Enter Email title missing");
        emailScreen.enterEmail("brajaautomation@gmail.com");
        emailScreen.clickNextButton();
        logger.info("✅ Email entered");

        // ==================== Password ====================

        logger.info("🔹 Setting password");
        Assert.assertTrue(passwordScreen.isPasswordTitleDisplayed(), "❌ Password title missing");
        passwordScreen.enterPassword("Test@123");
        passwordScreen.confirmPassword("Test@123");
        passwordScreen.clickNextButton();
        logger.info("✅ Password set");

        // ==================== Optional Hint ====================

        logger.info("🔹 Skipping optional hint");
        Assert.assertTrue(hintScreen.isOptionalHintTitleDisplayed(), "❌ Optional hint title missing");
        hintScreen.clickSkipButton();
        logger.info("✅ Hint skipped");

        // ==================== Vault Overview ====================

        logger.info("🔹 Validating Vault Overview");
        Assert.assertTrue(overviewScreen.isVaultOverviewTitleDisplayed(), "❌ Vault Overview missing");
        Assert.assertTrue(overviewScreen.isBackupInfoDisplayed(), "❌ Backup info missing");

        overviewScreen.clickBackupButton();
        overviewScreen.clickServerShareNextButton();
        overviewScreen.clickSelfCustodyNextButton();
        logger.info("✅ Vault Overview flow completed");

        // ==================== OTP ====================

        logger.info("🔹 Entering OTP");
        OtpRetryHandler.enterOtpWithRetry(
                DriverUtils.getDriver(),
                "brajaautomation@gmail.com",
                "dcef xkvk fgcn jsxx",
                "vultisig",
                3
        );
        logger.info("✅ OTP verified");

        // ==================== Backup Vault ====================

        Assert.assertTrue(vaultBackupScreen.isBackupVaultTitleDisplayed(), "❌ Backup Vault title missing");
        vaultBackupScreen.clickNextButton();

        // ==================== Downloads ====================

        Assert.assertTrue(downloadsPage.isDownloadsPageDisplayed(DriverUtils.getDriver()));
        Assert.assertTrue(downloadsPage.isVaultNamePresent(vaultName, DriverUtils.getDriver()));
        downloadsPage.clickSaveButton();
        logger.info("✅ Vault file saved");

        // ==================== Backup Guide ====================

        Assert.assertTrue(backupGuideScreen.isBackupGuideDisplayed(DriverUtils.getDriver()));
        backupGuideScreen.clickCheckBox();
        backupGuideScreen.clickNextButton(DriverUtils.getDriver());
        Thread.sleep(3000);

        // ==================== Settings ====================

        logger.info("🔹 Opening Settings");
        vaultsPage.clickSettingsButton();
        Thread.sleep(2000);

        settingsScreen.clickBackButton();
        Thread.sleep(1000);
        vaultsPage.clickSettingsButton();

        Assert.assertTrue(settingsScreen.isSettingsTitleDisplayed(), "❌ Settings title missing");

        settingsScreen.openShareVaultQr();
        Assert.assertTrue(settingsScreen.isShareVaultQrTitleDisplayed());
        Assert.assertTrue(settingsScreen.isVaultNameCorrect(vaultName));
        Assert.assertTrue(settingsScreen.isVultisigDomainDisplayed());
        Assert.assertTrue(settingsScreen.isQrDescriptionDisplayed());

        settingsScreen.clickShareButton();
        Assert.assertTrue(settingsScreen.isSharingImageTextDisplayed());
        settingsScreen.closeShareDialog();

        settingsScreen.clickSaveButton();
        Thread.sleep(1500);

        // ==================== Vault Settings ====================

        logger.info("🔹 Opening Vault Settings");
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isVaultSettingsTitleDisplayed());

        settingsScreen.clickBackButton();
        Thread.sleep(1000);
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);

        // ==================== Vault Management ====================

        Assert.assertTrue(settingsScreen.isVaultManagementDisplayed());
        Assert.assertTrue(settingsScreen.isDetailsTextDisplayed());
        Assert.assertTrue(settingsScreen.isRenameTextDisplayed());
        Assert.assertTrue(settingsScreen.isEditYourVaultNameDisplayed());

        // ==================== Biometrics ====================

        settingsScreen.clickBiometricsFastSignButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isEnableBiometricsFastSigningDisplayed());
        Assert.assertTrue(settingsScreen.isBiometricsSaveButtonDisabled());

        settingsScreen.clickBiometricsPasswordField();
        settingsScreen.enterBiometricsPassword("Test@123");
        settingsScreen.clickBiometricsSaveButton();
        Thread.sleep(2000);

        // ==================== Details ====================

        settingsScreen.clickDetailsButton();
        Thread.sleep(1500);

        Assert.assertTrue(settingsScreen.isVaultInfoDisplayed());
        Assert.assertTrue(settingsScreen.isVaultNameLabelDisplayed());
        Assert.assertTrue(settingsScreen.isVaultNameValueDisplayed(vaultName));
        Assert.assertTrue(settingsScreen.isVaultShareLabelDisplayed());
        Assert.assertTrue(settingsScreen.isVaultTypeLabelDisplayed());
        Assert.assertTrue(settingsScreen.isDklsDisplayed());

        settingsScreen.clickDetailsCopyIcon1();
        settingsScreen.clickDetailsCopyIcon2();

        settingsScreen.clickBackButton();

        // ==================== Rename ====================

        settingsScreen.clickRenameButton();
        Thread.sleep(1500);

        String renamedVault = SettingsScreen.getRenamedVaultName();
        settingsScreen.clickRenameClearIcon();
        settingsScreen.enterRenameText(renamedVault);
        settingsScreen.clickRenameSaveButton();
        Thread.sleep(1500);

        // ==================== Advanced ====================

        settingsScreen.clickAdvancedOption();
        Thread.sleep(1500);

        settingsScreen.clickSignTransactionOption();
        settingsScreen.enterSigningMethod("Test");
        settingsScreen.enterMessageToSign("Test");
        settingsScreen.clickSignTransactionContinueButton();

        settingsScreen.clickFastSignButton();
        settingsScreen.enterSignPassword("Test@123");
        settingsScreen.clickSignVerifyContinueButton();

        settingsScreen.clickCompleteButton();

        // ==================== On-Chain Security ====================

        settingsScreen.openOnChainSecurity();
        Assert.assertTrue(settingsScreen.isOnChainSecurityEnabledByDefault());

        settingsScreen.clickOnChainSecurityToggle();
        settingsScreen.clickContinueAnywayButton();

        settingsScreen.clickOnChainSecurityToggle();
        settingsScreen.clickOnChainSecurityGoBackButton();
        settingsScreen.clickOnChainSecurityBackButton();

        // ==================== Delete Vault ====================

        logger.info("🔴 Delete Vault flow");
        settingsScreen.clickDeleteVaultOption();
        Thread.sleep(1500);

        Assert.assertTrue(settingsScreen.isDeleteTitleDisplayed());
        Assert.assertTrue(settingsScreen.isDeleteVaultNameDisplayed(renamedVault));

        settingsScreen.clickDeleteCheckbox1();
        settingsScreen.clickDeleteCheckbox2();
        settingsScreen.clickDeleteCheckbox3();

        settingsScreen.clickFinalDeleteButton();

        logger.info("✅ E2E Create Fast Vault – Validate Settings completed successfully");
    }
}
