package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class EmailScreen extends BaseScreen {

    // 🔹 Step 1: Validate "Enter your e-mail" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enter your e-mail']")
    private WebElement enterEmailTitle;

    // 🔹 Step 2: Email text field
    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement emailTextField;

    // 🔹 Step 3: Next button
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Next\"]/parent::*")
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]")
    private static WebElement backButton;

    // 🔹 Text: "This email is only used to send the server backup"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='This email is only used to send the server backup']")
    private static WebElement serverBackupInfoText;

    // Clear text button (x icon)
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View")
    private static WebElement clearEmailButton;

    // Error text for wrong format
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Incorrect e-mail, please check']")
    private static WebElement incorrectEmailText;


    // 🔹 Constructor
    public EmailScreen(AppiumDriver driver) {
        super(driver);
    }

    // Click action for back button
    public static void clickBackButton() {
        backButton.click();
    }

    public static boolean isServerBackupInfoTextDisplayed() {
        return serverBackupInfoText.isDisplayed();
    }

    // Check whether Next button Disabled in default
    public boolean isNextButtonEnabled() {
         return nextButton.isEnabled();

    }

    // 🔹 Step 1: Validate title
    public boolean isEnterEmailDisplayed() {
        return enterEmailTitle.isDisplayed();
    }

    // 🔹 Step 2: Enter email
    public void enterEmail(String email) throws InterruptedException {
        Thread.sleep(1000); // wait for field to appear
        emailTextField.click();  // focus
        Thread.sleep(500);
        emailTextField.clear();  // clear if any text
        Thread.sleep(500);
        emailTextField.sendKeys(email);
        Thread.sleep(1000);
    }

    // 👉 Clear the email text field using clear button
    public  void clearEmailField() throws InterruptedException {
        clearEmailButton.click();
    }

    // 👉 Enter any given email
    public  void enterEmailScenario(String email) throws InterruptedException {
        Thread.sleep(1000);
        emailTextField.click();
        // If .clear() doesn’t work because of the container, clear with button first in test
        emailTextField.clear();
        emailTextField.sendKeys(email);
    }

    // 👉 Is error message "Incorrect e-mail, please check" visible?
    public boolean isIncorrectEmailMessageVisible() {
        try {
            return incorrectEmailText.isDisplayed();
        } catch (NoSuchElementException e) {
            return false; // not present = not visible
        }
    }


    // 🔹 Step 3: Click Next button
    public void clickNextButton() throws InterruptedException {
        Thread.sleep(1000);
        nextButton.click();
        Thread.sleep(1000);
    }
}
