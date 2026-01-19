package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class VaultBackupScreen extends BaseScreen {

    // 🔹 Step 1: "Backup Vault" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup Vault']")
    private WebElement backupVaultTitle;

    // 🔹 Step 2: Next button
    @AndroidFindBy(xpath = "//z1.q0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement nextButton;

    // 🔹 Constructor
    public VaultBackupScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate if "Backup Vault" title is displayed
    public boolean isBackupVaultTitleDisplayed() {
        return backupVaultTitle.isDisplayed();
    }

    // 🔹 Step 2: Click on Next button
    public void clickNextButton() {
        nextButton.click();
        System.out.println("✅ 'Next' button clicked on Vault Backup Screen");
    }
}
