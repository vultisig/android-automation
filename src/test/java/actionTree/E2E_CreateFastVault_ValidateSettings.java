package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.*;
import tests.BaseTest;
import utils.DriverUtils;
import utils.OtpRetryHandler;
import java.util.logging.Logger;
import org.vultisig.element_repo.HomePage;


public class E2E_CreateFastVault_ValidateSettings extends BaseTest {

    private static final Logger logger = Logger.getLogger(
            E2E_CreateFastVault_ValidateSettings.class.getName()
    );

    @Test(description = "Complete E2E flow: Create Fast Vault → Skip Onboarding & Hint → Backup Without Password → Validate Downloads → Validate Vaults")
    public void Validate_Settings() throws Exception {

        logger.info("🚀 Launching app and starting E2E flow...");

        // ---------------------- Initialize Screens ----------------------
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
        BackupChoiceScreen backupChoiceScreen = new BackupChoiceScreen(DriverUtils.getDriver());
        DownloadsPage downloadsPage = new DownloadsPage(DriverUtils.getDriver());
        BackupGuideScreen backupGuideScreen = new BackupGuideScreen(DriverUtils.getDriver());
        HomePage vaultsPage = new HomePage(DriverUtils.getDriver());
        SettingsScreen settingsScreen =  new SettingsScreen(DriverUtils.getDriver());

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
        logger.info("🔹 Skipping optional hint...");
        Assert.assertTrue(hintScreen.isOptionalHintTitleDisplayed(), "❌ 'Add an optional hint' title should be visible");
        hintScreen.clickSkipButton();
        logger.info("✅ Hint skipped");

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
        logger.info("🔹 Fetching OTP...");
        String userEmail = "brajaautomation@gmail.com";
        String appPassword = "msgh kcym rblv hmoc";
        String senderEmail = "vultisig";

        logger.info("🔹 Entering OTP with retry...");

        OtpRetryHandler.enterOtpWithRetry(
                DriverUtils.getDriver(),
                "brajaautomation@gmail.com",
                "msghkcymrblvhmoc",
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
        Assert.assertTrue(downloadsPage.isDownloadsPageDisplayed(DriverUtils.getDriver()), "❌ Downloads page not visible");
        Assert.assertTrue(downloadsPage.isVaultNamePresent(vaultName, DriverUtils.getDriver()), "❌ Vault name missing in file saving field");
        downloadsPage.clickSaveButton();
        logger.info("✅ Vault file saved successfully");

        // ---------------------- BackupGuideScreen ----------------------
        logger.info("🔹 Validating Backup Guide screen...");
        Assert.assertTrue(backupGuideScreen.isBackupGuideDisplayed(DriverUtils.getDriver()), "❌ Backup guide missing");
        backupGuideScreen.clickCheckBox();
        backupGuideScreen.clickNextButton(DriverUtils.getDriver());
        logger.info("✅ Backup Guide checkbox clicked and Next pressed");
        Thread.sleep(3000);

        // ---------------------- Settings Page---------------------
        logger.info("Validate the Settings Screen...");
        vaultsPage.clickSettingsButton();
        logger.info("Button CLicked ...");
        Thread.sleep(2000);
        logger.info("Click Back button and return back to the Settings screen ") ;
        settingsScreen.clickBackButton();
        Thread.sleep(1000);
        vaultsPage.clickSettingsButton();
        logger.info("Button CLicked ...");

        //-----------Validate Settings Text  ------------------

        // ----------- Validate Settings Screen Content -----------

        Assert.assertTrue(
                settingsScreen.isSettingsTitleDisplayed(),
                "❌ Settings title not displayed"
        );

        settingsScreen.openShareVaultQr();

        Assert.assertTrue(
                settingsScreen.isShareVaultQrTitleDisplayed(),
                "❌ Share Vault QR title not displayed"
        );

        Assert.assertTrue(
                settingsScreen.isVaultNameCorrect(vaultName),
                "❌ Vault name does not match previously created vault"
        );

        Assert.assertTrue(
                settingsScreen.isVultisigDomainDisplayed(),
                "❌ vultisig.com text not displayed"
        );

        Assert.assertTrue(
                settingsScreen.isQrDescriptionDisplayed(),
                "❌ QR description text not displayed"
        );

// ----------- Share Flow -----------

        settingsScreen.clickShareButton();

        Assert.assertTrue(
                settingsScreen.isSharingImageTextDisplayed(),
                "❌ Sharing image text not displayed"
        );

        settingsScreen.closeShareDialog();

// ----------- Save Flow -----------

        settingsScreen.clickSaveButton();
        Thread.sleep(1500);

        // ==================== Vault Settings ====================
        logger.info("🔹 Opening Vault Settings...");
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isVaultSettingsTitleDisplayed(), "❌ Vault Settings title not displayed");

        // Verify back button: go back and re-enter Vault Settings
        logger.info("🔹 Verifying back button on Vault Settings...");
        settingsScreen.clickBackButton();
        Thread.sleep(1000);
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isVaultSettingsTitleDisplayed(), "❌ Vault Settings title not displayed after back");

        // ----------- Vault Management -----------
        logger.info("🔹 Validating Vault Management section...");
        Assert.assertTrue(settingsScreen.isVaultManagementDisplayed(), "❌ Vault Management text not displayed");
        Assert.assertTrue(settingsScreen.isDetailsTextDisplayed(), "❌ Details text not displayed");
        Assert.assertTrue(settingsScreen.isRenameTextDisplayed(), "❌ Rename text not displayed");
        Assert.assertTrue(settingsScreen.isEditYourVaultNameDisplayed(), "❌ Edit your vault name not displayed");

        // ----------- Biometrics fast sign -----------
        logger.info("🔹 Validating Biometrics fast sign...");
        Assert.assertTrue(settingsScreen.isBiometricsFastSignDisplayed(), "❌ Biometrics fast sign text not displayed");
        settingsScreen.clickBiometricsFastSignButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isEnableBiometricsFastSigningDisplayed(), "❌ Enable Biometrics Fast Signing not displayed");
        Assert.assertTrue(settingsScreen.isBiometricsSaveButtonDisabled(), "❌ Save button should be disabled by default");
        Thread.sleep(1500);
        settingsScreen.clickBiometricsPasswordField();
        Thread.sleep(1500);
        settingsScreen.enterBiometricsPassword("Test@123");
        settingsScreen.clickBiometricsSaveButton();
        Thread.sleep(2000);
        Assert.assertTrue(settingsScreen.isBiometricsFastSignDisplayed(), "❌ Biometrics fast sign should be enabled back on Vault Settings");

        // ----------- Security & Other -----------
        logger.info("🔹 Validating Security and Other sections...");
        Assert.assertTrue(settingsScreen.isSecurityTextDisplayed(), "❌ Security text not displayed");
        Assert.assertTrue(settingsScreen.isBackupVaultShareTextDisplayed(), "❌ Backup Vault Share not displayed");
        Assert.assertTrue(settingsScreen.isBackupVaultShareDescDisplayed(), "❌ Backup Vault Share description not displayed");
        Assert.assertTrue(settingsScreen.isOtherTextDisplayed(), "❌ Other text not displayed");
        Assert.assertTrue(settingsScreen.isAdvancedTextDisplayed(), "❌ Advanced text not displayed");
        Assert.assertTrue(settingsScreen.isReshareChangeTssDisplayed(), "❌ Reshare, change TSS text not displayed");
        Assert.assertTrue(settingsScreen.isDeleteTextDisplayed(), "❌ Delete text not displayed");
        Assert.assertTrue(settingsScreen.isDeleteVaultShareDescDisplayed(), "❌ Delete vault share description not displayed");

        // ----------- Details -----------
        logger.info("🔹 Opening Details...");
        settingsScreen.clickDetailsButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isDetailsTextDisplayed(), "❌ Details title not displayed");
        Assert.assertTrue(settingsScreen.isVaultInfoDisplayed(), "❌ Vault Info not displayed");
        Assert.assertTrue(settingsScreen.isVaultNameLabelDisplayed(), "❌ Vault Name label not displayed");
        Assert.assertTrue(settingsScreen.isVaultNameValueDisplayed(vaultName), "❌ Vault name does not match: " + vaultName);
        Assert.assertTrue(settingsScreen.isVaultShareLabelDisplayed(), "❌ Vault Share label not displayed");
        Assert.assertTrue(settingsScreen.isVaultTypeLabelDisplayed(), "❌ Vault Type not displayed");
        Assert.assertTrue(settingsScreen.isDklsDisplayed(), "❌ DKLS not displayed");
        Assert.assertTrue(settingsScreen.isKeysTextDisplayed(), "❌ keys not displayed");
        Assert.assertTrue(settingsScreen.isEcdsaKeyDisplayed(), "❌ ECDSA Key not displayed");
        settingsScreen.clickDetailsCopyIcon1();
        Thread.sleep(500);
        Assert.assertTrue(settingsScreen.isEddsaKeyDisplayed(), "❌ EdDSA Key not displayed");
        settingsScreen.clickDetailsCopyIcon2();
        Thread.sleep(500);
        Assert.assertTrue(settingsScreen.isTwoOfTwoVaultSetupDisplayed(), "❌ 2-of-2 Vault Setup not displayed");
        Assert.assertTrue(settingsScreen.isSigner1Displayed(), "❌ Signer 1 not displayed");
        Assert.assertTrue(settingsScreen.isSigner2Displayed(), "❌ Signer 2 not displayed");
        settingsScreen.clickDetailsShareButton();
        Thread.sleep(1000);
        settingsScreen.pressAndroidBack();
        Thread.sleep(1000);
        settingsScreen.clickBackButton();
        Thread.sleep(1500);

        // ----------- Rename -----------
        logger.info("🔹 Rename vault flow...");
        settingsScreen.clickRenameButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isRenameSectionDisplayed(), "❌ Rename section not displayed");
        Assert.assertTrue(settingsScreen.isRenameEditTextSameAsVaultName(vaultName), "❌ Rename field should show vault name: " + vaultName);
        settingsScreen.clickRenameClearIcon();
        Thread.sleep(500);
        settingsScreen.clickRenameTextField();
        String renamedVault = SettingsScreen.getRenamedVaultName();
        settingsScreen.enterRenameText(renamedVault);
        settingsScreen.clickRenameSaveButton();
        Thread.sleep(1500);
        vaultsPage.clickSettingsButton();
        logger.info("Button CLicked ...");
        Thread.sleep(2000);
        logger.info("🔹 Opening Vault Settings...");
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);

        //----------- Off the Biometrics ---------
        settingsScreen.clickBiometricsFastSignButton();


        // ----------- Backup vault share -----------
        logger.info("🔹 Backup vault share option...");
        settingsScreen.clickBackupVaultShareOption();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isSelectVaultsToBackupDisplayed(), "❌ Select vaults to backup not displayed");
        Assert.assertTrue(settingsScreen.isChooseVaultBackupDescDisplayed(), "❌ Choose vault backup description not displayed");
        Assert.assertTrue(settingsScreen.isThisVaultOnlyDisplayed(), "❌ This Vault Only not displayed");
        settingsScreen.clickRenameBackButton();
        Thread.sleep(1500);

        // ----------- Advanced & Sign Transaction -----------
        logger.info("🔹 Advanced and Sign Transaction...");
        settingsScreen.clickAdvancedOption();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isSignTransactionTextDisplayed(), "❌ Sign Transaction not displayed");
        Assert.assertTrue(settingsScreen.isSignCustomMessageDisplayed(), "❌ Sign custom message not displayed");
        Assert.assertTrue(settingsScreen.isOnChainSecurityDisplayed(), "❌ On-chain Security not displayed");
        Assert.assertTrue(settingsScreen.isManageOnChainSecurityDisplayed(), "❌ Manage your on-chain security not displayed");
        settingsScreen.clickSignTransactionOption();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isSignTransactionSectionDisplayed(), "❌ Sign Transaction section not displayed");
        settingsScreen.clickSigningMethodTextField();
        settingsScreen.enterSigningMethod("Test");
        settingsScreen.clickMessageToSignTextField();
        settingsScreen.enterMessageToSign("Test");
        settingsScreen.clickSignTransactionContinueButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isVerifyTextDisplayed(), "❌ Verify text not displayed");
        settingsScreen.clickFastSignButton();
        Thread.sleep(1000);
        Assert.assertTrue(settingsScreen.isEnterYourPasswordDisplayed(), "❌ Enter your password not displayed");
        settingsScreen.enterSignPassword("Test@123");
        settingsScreen.clickSignVerifyContinueButton();
        Thread.sleep(1500);
        Assert.assertTrue(settingsScreen.isOverviewDisplayed(), "❌ Overview not displayed");
        Assert.assertTrue(settingsScreen.isMethodDisplayed(), "❌ Method not displayed");
        Assert.assertTrue(settingsScreen.isMessageLabelDisplayed(), "❌ Message not displayed");
        Assert.assertTrue(settingsScreen.isSignatureDisplayed(), "❌ Signature not displayed");
        settingsScreen.clickCompleteButton();
        logger.info("✅ Validate Settings Complete – all Vault Settings flows finished");

        //-- On sign- Transaction
        vaultsPage.clickSettingsButton();
        settingsScreen.clickVaultSettings();
        Thread.sleep(1500);
        settingsScreen.clickAdvancedOption();
        Thread.sleep(20000);


    }
    
}
