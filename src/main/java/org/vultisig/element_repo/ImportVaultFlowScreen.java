package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ImportVaultFlowScreen extends BaseScreen {

    // 🔹 Import title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Import']")
    private WebElement importTitle;

    // 🔹 Import icon
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Import your vault share\"]/parent::android.view.View")
    private WebElement importIcon;

    // 🔹 Downloads screen title
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Downloads'])[2]")
    private WebElement downloadsTitle;

    // 🔹 Back button
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'Supported file types')]/following-sibling::android.view.View")
    private WebElement backButton;

    // 🔹 Existing vault
    @AndroidFindBy(xpath = "(//android.widget.ImageView[@resource-id='com.google.android.documentsui:id/icon_thumb'])[1]")
    private WebElement existingVault;

    // 🔹 Continue button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement continueButton;

    // 🔹 Verify your Server Share Password title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Verify your Server Share Password']")
    private WebElement verifyPasswordTitle;

    // 🔹 Password field
    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement passwordField;

    // 🔹 Verify button
    @AndroidFindBy(xpath = "//android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[2]/android.view.View[3]")
    private WebElement verifyButton;

    // 🔹 Constructor
    public ImportVaultFlowScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Check if Import title is displayed
    public boolean isImportTitleDisplayed() {
        return importTitle.isDisplayed();
    }

    // 🔹 Click Back button and exit driver
    public void clickBackAndExit() {
        backButton.click();
        System.out.println("✅ Clicked back and exited the driver successfully");
    }

    // 🔹 Click Import icon
    public void clickImportIcon() {
        importIcon.click();
        System.out.println("✅ Import icon clicked");
    }

    // 🔹 Wait for Downloads screen and validate title
    public boolean waitForDownloadsTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(downloadsTitle));
        return downloadsTitle.isDisplayed();
    }

    // 🔹 Click on existing vault
    public void clickExistingVault() {
        existingVault.click();
        System.out.println("✅ Existing vault selected");
    }

    // 🔹 Click Continue button
    public void clickContinueButton() {
        continueButton.click();
        System.out.println("✅ Continue button clicked");
    }

    // 🔹 Wait for Verify Password title
    public boolean waitForVerifyPasswordTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(verifyPasswordTitle));
        return verifyPasswordTitle.isDisplayed();
    }

    // 🔹 Click Password field

    public void clickPasswordField(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("✅ password confirmed");
    }

    // 🔹 Click Verify button
    public void clickVerifyButton() {
        verifyButton.click();
        System.out.println("✅ Verify button clicked");
    }
}
