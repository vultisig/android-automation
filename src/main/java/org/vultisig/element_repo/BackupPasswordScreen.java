package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;

public class BackupPasswordScreen extends BaseScreen {

    // 🔹 Step 1: Validate "Password" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Password']")
    private WebElement passwordTitle;

    // 🔹 Step 2: Password input field
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement passwordField;

    // 🔹 Step 3: Confirm password input field
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[2]/android.view.View/android.widget.EditText")
    private WebElement confirmPasswordField;

    // 🔹 Step 4: Next button
    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement nextButton;

    // 🔹 Constructor
    public BackupPasswordScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate title
    public boolean isPasswordTitleDisplayed() {
        return passwordTitle.isDisplayed();
    }

    // 🔹 Step 2: Enter password
    public void enterPassword(String password) {
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
        System.out.println("✅ Backup password entered");
    }

    // 🔹 Step 3: Confirm password
    public void confirmPassword(String password) {
        confirmPasswordField.click();
        confirmPasswordField.clear();
        confirmPasswordField.sendKeys(password);
        System.out.println("✅ Backup password confirmed");
    }

    // 🔹 Step 4: Click Next button
    public void clickNextButton() {
        nextButton.click();
        System.out.println("✅ Backup Next button clicked");
    }
}
