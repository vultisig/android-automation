package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import java.time.Duration;
import java.util.logging.Logger;

public class SettingsScreen extends BaseScreen {
    private static final Logger logger = Logger.getLogger(SettingsScreen.class.getName());

    // 🔹 Constructor
    public SettingsScreen(AppiumDriver driver) {
        super(driver);
    }
    // 🔹 Back button on Settings screen
    @AndroidFindBy(
            xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]"
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
            xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]"
    )
    private WebElement openShareQrButton;

    // Share button
    @AndroidFindBy(
            xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]"
    )
    private WebElement shareButton;

    // Save button
    @AndroidFindBy(
            xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]"
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


}
