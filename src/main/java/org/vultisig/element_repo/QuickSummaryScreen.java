package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class QuickSummaryScreen extends BaseScreen {

    // 🔹 Step 1: Validate "I have read and understand what to do"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='I have read and understand what to do']")
    private WebElement readAndUnderstandWhatToDo;

    // 🔹 Step 2: Checkbox
    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View")
    private WebElement checkBox;

    // 🔹 Step 3: "Create your vault" button
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create your vault']")
    private WebElement createVault;

    // 🔹 Constructor
    public QuickSummaryScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate if title text is displayed
    public boolean isReadAndUnderstandWhatToDoDisplayed() {
        return readAndUnderstandWhatToDo.isDisplayed();
    }

    // 🔹 Step 2: Validate if checkbox is displayed
    public boolean isCheckboxDisplayed() {
        return checkBox.isDisplayed();
    }

    public void validateDefaultStates() {
        try {
            boolean isCheckboxEnabled = checkBox.isEnabled();
            boolean isCreateVaultEnabled = createVault.isEnabled();

            if (!isCheckboxEnabled) {
                System.out.println("✅ Checkbox is disabled by default (expected).");
            } else {
                System.out.println("⚠️ Checkbox is enabled by default (unexpected).");
            }

            if (!isCreateVaultEnabled) {
                System.out.println("✅ 'Create Vault' button is disabled by default (expected).");
            } else {
                System.out.println("⚠️ 'Create Vault' button is enabled by default (unexpected).");
            }

        } catch (Exception e) {
            System.out.println("❌ Failed to validate default states: " + e.getMessage());
        }
    }

    // 🔹 Step 3: Click checkbox and wait for "Create Vault" button to appear
    public void clickCheckboxAndWaitForCreateVault() {
        checkBox.click();
        System.out.println("✅ Checkbox clicked on Quick Summary Screen");

        // Wait for the Create Vault button to become visible and clickable
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(createVault));
        wait.until(ExpectedConditions.elementToBeClickable(createVault));

        System.out.println("⏳ Waiting for 'Create Vault' button to appear...");
    }

    // 🔹 Step 4: Click "Create Vault"
    public void clickCreateVault() {
        createVault.click();
        System.out.println("✅ 'Create Vault' button clicked");
    }
}
