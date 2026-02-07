package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;
import java.util.logging.Logger;

public class SettingsScreen extends BaseScreen {
    private static final Logger logger = Logger.getLogger(SettingsScreen.class.getName());

    // 🔹 Constructor
    public SettingsScreen(AppiumDriver driver) {
        super(driver);
    }
    // 🔹 Back button on Settings screen (same xpath used on Vault Settings and other screens)
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]"
    )
    private WebElement backButton;
    // ---------- Titles / Texts ----------

    // Settings title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Settings']")
    private WebElement settingsTitle;

    // Share Vault QR title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share Vault QR']")
    private WebElement shareVaultQrTitle;

    // Vault name text (dynamic – validated via method)
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Fast Vault')]")
    private WebElement vaultNameText;

    // Domain text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='vultisig.com']")
    private WebElement vultisigDomainText;

    // QR description text
    @AndroidFindBy(
            xpath = "//android.widget.TextView[@text='This QR Code lets you share a view-only version of your Vault']"
    )
    private WebElement qrDescriptionText;

// ---------- Buttons ----------

    // Open Share Vault QR section
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]"
    )
    private WebElement openShareQrButton;

    // Share button
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]"
    )
    private WebElement shareButton;

    // Save button
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]"
    )
    private WebElement saveButton;

    // Sharing image text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sharing image']")
    private WebElement sharingImageText;

    // Tap outside to dismiss share dialog
    @AndroidFindBy(xpath = "//android.widget.ScrollView[@resource-id='android:id/contentPanel']")
    private WebElement outsideTapArea;


    // 🔹 Click Back button
    public void clickBackButton() {
        backButton.click();
        logger.info("✅ Back button clicked on Settings screen");
    }

    // ---------- Validations ----------

    public boolean isSettingsTitleDisplayed() {
        return settingsTitle.isDisplayed();
    }

    public boolean isShareVaultQrTitleDisplayed() {
        return shareVaultQrTitle.isDisplayed();
    }

    public boolean isVaultNameCorrect(String expectedVaultName) {
        return vaultNameText.getText().equals(expectedVaultName);
    }

    public boolean isVultisigDomainDisplayed() {
        return vultisigDomainText.isDisplayed();
    }

    public boolean isQrDescriptionDisplayed() {
        return qrDescriptionText.isDisplayed();
    }

    public boolean isSharingImageTextDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(sharingImageText));
        return sharingImageText.isDisplayed();
    }

// ---------- Actions ----------

    public void openShareVaultQr() {
        openShareQrButton.click();
        logger.info("✅ Share Vault QR section opened");
    }

    public void clickShareButton() {
        shareButton.click();
        logger.info("✅ Share button clicked");
    }

    public void closeShareDialog() {
        outsideTapArea.click();
        logger.info("✅ Share dialog dismissed");
    }

    public void clickSaveButton() {
        saveButton.click();
        logger.info("✅ Save button clicked");
    }

    // ==================== Vault Settings (from Settings screen) ====================

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[1]")
    private WebElement vaultSettingsButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Settings\"]")
    private WebElement vaultSettingsTitle;

    // --- Vault Management ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Management\"]")
    private WebElement vaultManagementText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Details\"]")
    private WebElement detailsText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Rename\"]")
    private WebElement renameText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Edit your vault name\"]")
    private WebElement editYourVaultNameText;

    // --- Biometrics fast sign ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Biometrics fast sign\"]")
    private WebElement biometricsFastSignText;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[5]/android.view.View/android.view.View")
    private WebElement biometricsFastSignButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Enable Biometrics Fast Signing\"]")
    private WebElement enableBiometricsFastSigningText;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[3]")
    private WebElement biometricsSaveButton;

    // Use EditText itself; /android.view.View was the non-editable child and caused InvalidElementStateException
    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement biometricsPasswordField;

    // --- Security ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Security\"]")
    private WebElement securityText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Backup Vault Share\"]")
    private WebElement backupVaultShareText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Back up your Vault Share to device or server.\"]")
    private WebElement backupVaultShareDescText;

    // --- Other ---
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Other\"]")
    private WebElement otherText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Advanced\"]")
    private WebElement advancedText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Reshare, change TSS, or sign messages.\"]")
    private WebElement reshareChangeTsstext;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Delete\"]")
    private WebElement deleteText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Delete your vault share permanently\"]")
    private WebElement deleteVaultShareDescText;

    // --- Details section (Vault Settings) ---
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[1]")
    private WebElement detailsButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Info\"]")
    private WebElement vaultInfoText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Name\"]")
    private WebElement vaultNameLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Share\"]")
    private WebElement vaultShareLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Type\"]")
    private WebElement vaultTypeLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"DKLS\"]")
    private WebElement dklsText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"keys\"]")
    private WebElement keysText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"ECDSA Key\"]")
    private WebElement ecdsaKeyText;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[4]/android.view.View")
    private WebElement detailsCopyIcon1;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"EdDSA Key\"]")
    private WebElement eddsaKeyText;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[5]/android.view.View")
    private WebElement detailsCopyIcon2;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"2-of-2 Vault Setup\"]")
    private WebElement twoOfTwoVaultSetupText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Signer 1\"]")
    private WebElement signer1Text;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Signer 2\"]")
    private WebElement signer2Text;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement detailsShareButton;

    // --- Rename ---
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View[3]")
    private WebElement renameButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text=\"Fast Vault #702\"]/android.view.View")
    private WebElement renameEditTextWrapper;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View")
    private WebElement renameClearIcon;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement renameTextField;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement renameSaveButton;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View[1]")
    private WebElement renameBackButton;

    // --- Backup vault share option ---
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View")
    private WebElement backupVaultShareOption;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Select vaults to backup\"]")
    private WebElement selectVaultsToBackupText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Choose whether to back up just this vault or all vaults in your app.\"]")
    private WebElement chooseVaultBackupDescText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"This Vault Only\"]")
    private WebElement thisVaultOnlyText;

    // --- Advanced option ---
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[3]/android.view.View")
    private WebElement advancedOption;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign Transaction\"]")
    private WebElement signTransactionText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Sign custom message\"]")
    private WebElement signCustomMessageText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"On-chain Security\"]")
    private WebElement onChainSecurityText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Manage your on-chain security\"]")
    private WebElement manageOnChainSecurityText;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View/android.view.View[1]")
    private WebElement signTransactionOption;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement signingMethodTextField;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText")
    private WebElement messageToSignTextField;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[3]")
    private WebElement signTransactionContinueButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Verify\"]")
    private WebElement verifyText;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View[1]")
    private WebElement fastSignButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Enter your password\"]")
    private WebElement enterYourPasswordText;

    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]")
    private WebElement signVerifyContinueButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Overview\"]")
    private WebElement overviewText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Method\"]")
    private WebElement methodText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Message\"]")
    private WebElement messageText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Signature\"]")
    private WebElement signatureText;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement completeButton;

    //---------On-chain security -----------
    // --- On-chain Security option (Advanced section) ---
    @AndroidFindBy(
            xpath = "//android.widget.ScrollView/android.view.View/android.view.View[3]"
    )
    private WebElement onChainSecurityOption;

    // Title & description
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Security']")
    private WebElement onChainSecurityTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Manage your on-chain security']")
    private WebElement manageOnChainSecurityTitle;

    @AndroidFindBy(
            xpath = "//android.widget.TextView[@text=\"You can disable your realtime on-chain security provided by Blockaid.\"]"
    )
    private WebElement onChainSecurityDescription;

    // Toggle (same XPath used for enable / disable)
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]"
    )
    private WebElement onChainSecurityToggle;

    // Disable warning texts
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='On-chain security disabled']")
    private WebElement onChainSecurityDisabledTitle;

    @AndroidFindBy(
            xpath = "//android.widget.TextView[@text=\"Disabling on-chain security means Blockaid can't verify transactions. You'll be less protected\"]"
    )
    private WebElement onChainSecurityDisabledDescription;

    // Continue anyway button
    @AndroidFindBy(
            xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]"
    )
    private WebElement continueAnywayButton;
    // Go Back button (On-chain Security warning screen)
    @AndroidFindBy(
            xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]"
    )
    private WebElement onChainSecurityGoBackButton;

    // Back button on On-chain Security screen
    @AndroidFindBy(xpath = "//android.widget.Button")
    private WebElement onChainSecurityBackButton;

    //-------Delete Module__________

    // ==================== Delete Vault ====================

    // Delete option click (from Vault Settings list)
    @AndroidFindBy(
            xpath = "//android.widget.ScrollView/android.view.View[4]/android.view.View"
    )
    private WebElement deleteVaultOption;

    // Delete screen title
    @AndroidFindBy(
            xpath = "(//android.widget.TextView[@text='Delete'])[1]"
    )
    private WebElement deleteTitle;

    // Vault name shown on delete screen (dynamic)
    @AndroidFindBy(xpath = "//android.widget.TextView")
    private WebElement deleteVaultNameText;

    // Labels & values
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Vault Value:']")
    private WebElement vaultValueLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Vault Share:']")
    private WebElement vaultShareLabelDeleteScreen;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Share 1-of-2']")
    private WebElement shareOneOfTwoText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ECDSA Key:']")
    private WebElement ecdsaKeyLabel;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='EdDSA Key:']")
    private WebElement eddsaKeyLabel;

    // Acknowledgement texts
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I am aware that the vault will be deleted permanently']")
    private WebElement awarePermanentDeleteText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I am aware that I can lose funds']")
    private WebElement awareLoseFundsText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I have made a vault backup']")
    private WebElement vaultBackupConfirmationText;

    // Delete button (disabled by default)
    @AndroidFindBy(
            xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]"
    )
    private WebElement deleteButton;

    // Checkboxes
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[7]/android.view.View")
    private WebElement deleteCheckbox1;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[8]/android.view.View")
    private WebElement deleteCheckbox2;

    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[9]/android.view.View")
    private WebElement deleteCheckbox3;


    // ---------- Vault Settings actions ----------

    public void clickVaultSettings() {
        waitForVisibility(vaultSettingsButton).click();
        logger.info("✅ Vault Settings clicked");
    }

    public boolean isVaultSettingsTitleDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Settings\"]");
    }

    public boolean isVaultManagementDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Management\"]");
    }

    public boolean isDetailsTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Details\"]");
    }

    public boolean isRenameTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Rename\"]");
    }

    public boolean isEditYourVaultNameDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Edit your vault name\"]");
    }

    public boolean isBiometricsFastSignDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Biometrics fast sign\"]");
    }

    public void clickBiometricsFastSignButton() {
        waitForVisibility(biometricsFastSignButton).click();
        logger.info("✅ Biometrics fast sign button clicked");
    }

    public boolean isEnableBiometricsFastSigningDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Enable Biometrics Fast Signing\"]");
    }

    public boolean isBiometricsSaveButtonDisabled() {
        return !biometricsSaveButton.isEnabled();
    }

    public void clickBiometricsPasswordField() {
        waitForVisibility(biometricsPasswordField).click();
    }

    public void enterBiometricsPassword(String password) {
        WebElement field = waitForVisibility(biometricsPasswordField);
        field.click();
        field.clear();
        field.sendKeys(password);
        logger.info("✅ Biometrics password entered");
    }

    public void clickBiometricsSaveButton() {
        waitForVisibility(biometricsSaveButton).click();
        logger.info("✅ Biometrics save button clicked");
    }

    public boolean isSecurityTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Security\"]");
    }

    public boolean isBackupVaultShareTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Backup Vault Share\"]");
    }

    public boolean isBackupVaultShareDescDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Back up your Vault Share to device or server.\"]");
    }

    public boolean isOtherTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Other\"]");
    }

    public boolean isAdvancedTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Advanced\"]");
    }

    public boolean isReshareChangeTssDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Reshare, change TSS, or sign messages.\"]");
    }

    public boolean isDeleteTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Delete\"]");
    }

    public boolean isDeleteVaultShareDescDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Delete your vault share permanently\"]");
    }

    public void clickDetailsButton() {
        waitForVisibility(detailsButton).click();
        logger.info("✅ Details clicked");
    }

    public boolean isVaultInfoDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Info\"]");
    }

    public boolean isVaultNameLabelDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Name\"]");
    }

    public boolean isVaultNameValueDisplayed(String expectedVaultName) {
        return waitForElementSafe("//android.widget.TextView[@text=\"" + expectedVaultName + "\"]");
    }

    public boolean isVaultShareLabelDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Share\"]");
    }

    public boolean isVaultTypeLabelDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Vault Type\"]");
    }

    public boolean isDklsDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"DKLS\"]");
    }

    public boolean isKeysTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"keys\"]");
    }

    public boolean isEcdsaKeyDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"ECDSA Key\"]");
    }

    public void clickDetailsCopyIcon1() {
        waitForVisibility(detailsCopyIcon1).click();
        logger.info("✅ Details copy icon 1 clicked");
    }

    public boolean isEddsaKeyDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"EdDSA Key\"]");
    }

    public void clickDetailsCopyIcon2() {
        waitForVisibility(detailsCopyIcon2).click();
        logger.info("✅ Details copy icon 2 clicked");
    }

    public boolean isTwoOfTwoVaultSetupDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"2-of-2 Vault Setup\"]");
    }

    public boolean isSigner1Displayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Signer 1\"]");
    }

    public boolean isSigner2Displayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Signer 2\"]");
    }

    public void clickDetailsShareButton() {
        waitForVisibility(detailsShareButton).click();
        logger.info("✅ Details share button clicked");
    }

    public void clickRenameButton() {
        waitForVisibility(renameButton).click();
        logger.info("✅ Rename button clicked");
    }

    public boolean isRenameSectionDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Rename Vault\"]");
    }

    public boolean isRenameEditTextSameAsVaultName(String vaultName) {
        try {
            WebElement el = driver.findElement(By.xpath("//android.widget.EditText[@text=\"" + vaultName + "\"]"));
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickRenameClearIcon() {
        waitForVisibility(renameClearIcon).click();
        logger.info("✅ Rename clear icon clicked");
    }

    public void clickRenameTextField() {
        waitForVisibility(renameTextField).click();
    }

    public void enterRenameText(String text) {
        renameTextField.sendKeys(text);
        logger.info("✅ Rename text entered: " + text);
    }

    public void clickRenameSaveButton() {
        waitForVisibility(renameSaveButton).click();
        logger.info("✅ Rename save button clicked");
    }

    public void clickRenameBackButton() {
        waitForVisibility(renameBackButton).click();
        logger.info("✅ Rename back button clicked");
    }

    public void clickBackupVaultShareOption() {
        waitForVisibility(backupVaultShareOption).click();
        logger.info("✅ Backup vault share option clicked");
    }

    public boolean isSelectVaultsToBackupDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Select vaults to backup\"]");
    }

    public boolean isChooseVaultBackupDescDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Choose whether to back up just this vault or all vaults in your app.\"]");
    }

    public boolean isThisVaultOnlyDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"This Vault Only\"]");
    }

    public void clickAdvancedOption() {
        waitForVisibility(advancedOption).click();
        logger.info("✅ Advanced option clicked");
    }

    public boolean isSignTransactionTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Sign Transaction\"]");
    }

    public boolean isSignCustomMessageDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Sign custom message\"]");
    }

    public boolean isOnChainSecurityDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"On-chain Security\"]");
    }

    public boolean isManageOnChainSecurityDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Manage your on-chain security\"]");
    }

    public void clickSignTransactionOption() {
        waitForVisibility(signTransactionOption).click();
        logger.info("✅ Sign Transaction option clicked");
    }

    public boolean isSignTransactionSectionDisplayed() {
        try {
            return driver.findElement(By.xpath("//android.widget.TextView[@text=\"Sign message\"]")).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickSigningMethodTextField() {
        waitForVisibility(signingMethodTextField).click();
    }

    public void enterSigningMethod(String text) {
        signingMethodTextField.sendKeys(text);
    }

    public void clickMessageToSignTextField() {
        waitForVisibility(messageToSignTextField).click();
    }

    public void enterMessageToSign(String text) {
        messageToSignTextField.sendKeys(text);
    }

    public void clickSignTransactionContinueButton() {
        waitForVisibility(signTransactionContinueButton).click();
        logger.info("✅ Sign Transaction continue clicked");
    }

    public boolean isVerifyTextDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Verify\"]");
    }

    public void clickFastSignButton() {
        waitForVisibility(fastSignButton).click();
        logger.info("✅ Fast sign button clicked");
    }

    public boolean isEnterYourPasswordDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Enter your password\"]");
    }

    public void enterSignPassword(String password) {
        WebElement pwdField = waitForVisibility(driver.findElement(By.xpath("//android.widget.EditText")));
        pwdField.click();
        pwdField.clear();
        pwdField.sendKeys(password);
        logger.info("✅ Sign password entered");
    }

    public void clickSignVerifyContinueButton() {
        waitForVisibility(signVerifyContinueButton).click();
        logger.info("✅ Sign verify continue clicked");
    }

    public boolean isOverviewDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Overview\"]");
    }

    public boolean isMethodDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Method\"]");
    }

    public boolean isMessageLabelDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Message\"]");
    }

    public boolean isSignatureDisplayed() {
        return waitForElementSafe("//android.widget.TextView[@text=\"Signature\"]");
    }

    public void clickCompleteButton() {
        waitForVisibility(completeButton).click();
        logger.info("✅ Complete button clicked");
    }

    /** Helper: wait for element by xpath (safe, returns false if not found). */
    private boolean waitForElementSafe(String xpath) {
        try {
            WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement el = w.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            return el != null && el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /** Generate renamed vault name with random number 1–1000. */
    public static String getRenamedVaultName() {
        return "Renamed Vault name " + (new Random().nextInt(1000) + 1);
    }

    /** Press Android device back key (e.g. to dismiss share dialog). */
    public void pressAndroidBack() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        logger.info("✅ Android back key pressed");
    }

    //------On-Chain Security ----
    public void openOnChainSecurity() {
        waitForVisibility(onChainSecurityOption).click();
        logger.info("✅ On-chain Security option opened");
    }

    public void clickOnChainSecurityToggle() {
        logger.info("Clicking On-chain Security toggle");

        driver.findElement(
                By.xpath(
                        "//z1.s0/android.view.View/android.view.View/" +
                                "android.view.View/android.view.View/android.view.View[1]/android.view.View[1]"
                )
        ).click();

        logger.info("On-chain Security toggle clicked");
    }



    public void clickContinueAnywayButton() {
        waitForVisibility(continueAnywayButton).click();
        logger.info("✅ Continue Anyway button clicked");
    }
    public boolean isOnChainSecurityTitleDisplayed() {
        return onChainSecurityTitle.isDisplayed();
    }


    public boolean isOnChainSecurityDescriptionDisplayed() {
        return onChainSecurityDescription.isDisplayed();
    }

    /**
     * Toggle should be visible & enabled by default
     * (UI doesn't expose true state, so visibility = enabled assumption)
     */
    public boolean isOnChainSecurityEnabledByDefault() {
        return onChainSecurityToggle.isEnabled();
    }

    public boolean isOnChainSecurityDisabledTitleDisplayed() {
        return onChainSecurityDisabledTitle.isDisplayed();
    }

    public boolean isOnChainSecurityDisabledDescriptionDisplayed() {
        return onChainSecurityDisabledDescription.isDisplayed();
    }

    public void clickOnChainSecurityGoBackButton() {
        waitForVisibility(onChainSecurityGoBackButton).click();
        logger.info("✅ On-chain Security Go Back button clicked");
    }

    public void clickOnChainSecurityBackButton() {
        waitForVisibility(onChainSecurityBackButton).click();
        logger.info("✅ Back button clicked on On-chain Security screen");
    }


    //---Delete Vault Module---------
    public void clickDeleteVaultOption() {
        waitForVisibility(deleteVaultOption).click();
        logger.info("✅ Delete vault option clicked");
    }

    public void clickDeleteCheckbox1() {
        waitForVisibility(deleteCheckbox1).click();
        logger.info("✅ Delete checkbox 1 clicked");
    }

    public void clickDeleteCheckbox2() {
        waitForVisibility(deleteCheckbox2).click();
        logger.info("✅ Delete checkbox 2 clicked");
    }

    public void clickDeleteCheckbox3() {
        waitForVisibility(deleteCheckbox3).click();
        logger.info("✅ Delete checkbox 3 clicked");
    }

    public void clickFinalDeleteButton() {
        waitForVisibility(deleteButton).click();
        logger.info("🔥 Final Delete button clicked");
    }

    public boolean isDeleteTitleDisplayed() {
        return deleteTitle.isDisplayed();
    }

    public boolean isDeleteVaultNameDisplayed(String expectedVaultName) {
        return waitForElementSafe("//android.widget.TextView[@text=\"" + expectedVaultName + "\"]");
    }

    public boolean isVaultValueLabelDisplayed() {
        return vaultValueLabel.isDisplayed();
    }

    public boolean isVaultShareLabelDeleteScreenDisplayed() {
        return vaultShareLabelDeleteScreen.isDisplayed();
    }

    public boolean isShareOneOfTwoDisplayed() {
        return shareOneOfTwoText.isDisplayed();
    }

    public boolean isEcdsaKeyLabelDisplayed() {
        return ecdsaKeyLabel.isDisplayed();
    }

    public boolean isEddsaKeyLabelDisplayed() {
        return eddsaKeyLabel.isDisplayed();
    }

    public boolean isPermanentDeleteWarningDisplayed() {
        return awarePermanentDeleteText.isDisplayed();
    }

    public boolean isLoseFundsWarningDisplayed() {
        return awareLoseFundsText.isDisplayed();
    }

    public boolean isVaultBackupConfirmationDisplayed() {
        return vaultBackupConfirmationText.isDisplayed();
    }

    public boolean isDeleteButtonDisabledByDefault() {
        return !deleteButton.isEnabled();
    }


}
