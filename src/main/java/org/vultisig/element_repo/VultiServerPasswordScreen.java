package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class VultiServerPasswordScreen extends BaseScreen {

    // 🔹 Step 1: Validate "Vultiserver Password" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Vultiserver Password']")
    private WebElement passwordTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password cannot be reset or recovered']")
    private WebElement passwordResetWarningText;

    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]")
    private static WebElement backButton;

    // 🔹 Step 2: Password field
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement passwordField;

    // 🔹 Step 3: Confirm password field
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View/android.widget.EditText")
    private WebElement confirmPasswordField;

    // 🔹 Step 4: Next button
    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement nextButton;

    // Eye icon for password visibility toggle
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View/android.view.View")
    private WebElement passwordEyeIcon;

    // Eye icon for confirm-password visibility toggle
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View/android.view.View")
    private WebElement confirmPasswordEyeIcon;

    // "Passwords do not match" error text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Passwords do not match']")
    private WebElement passwordsDoNotMatchText;

    // 🔹 Constructor
    public VultiServerPasswordScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate title
    public boolean isPasswordTitleDisplayed() {
        return passwordTitle.isDisplayed();
    }

    // 🔹 Step 2: Enter password
    public void enterPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        passwordField.click();
        Thread.sleep(500);
        passwordField.clear();
        Thread.sleep(500);
        passwordField.sendKeys(password);
        Thread.sleep(1000);
    }

    // 🔹 Step 3: Re-enter password
    public void confirmPassword(String password) throws InterruptedException {
        Thread.sleep(1000);
        confirmPasswordField.click();
        Thread.sleep(500);
        confirmPasswordField.clear();
        Thread.sleep(500);
        confirmPasswordField.sendKeys(password);
        Thread.sleep(1000);
    }

    // 🔹 Step 4: Click Next button
    public void clickNextButton() throws InterruptedException {
        Thread.sleep(1000);
        nextButton.click();
        Thread.sleep(1000);
    }

    public boolean isPasswordResetWarningDisplayed() {
        try {
            return passwordResetWarningText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Click action for back button
    public static void clickBackButton() {
        backButton.click();
    }

    // Check whether Next button Disabled in default
    public boolean isNextButtonEnabled() {
        return nextButton.isEnabled();

    }

    // Check if the mismatch error is displayed
    public boolean isPasswordMismatchErrorDisplayed() {
        try {
            return passwordsDoNotMatchText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Click eye icon for password field
    public void togglePasswordEyeIcon(int times) {
        for (int i = 0; i < times; i++) {
            passwordEyeIcon.click();
        }
    }

    // Click eye icon for confirm-password field
    public void toggleConfirmPasswordEyeIcon(int times) {
        for (int i = 0; i < times; i++) {
            confirmPasswordEyeIcon.click();
        }
    }

}
