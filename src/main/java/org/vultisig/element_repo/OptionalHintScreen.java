package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OptionalHintScreen extends BaseScreen {

    // 🔹 Step 1: Validate "Add an optional hint" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Add a optional hint']")
    private WebElement optionalHintTitle;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='This will be shown in case you forget your password']")
    private WebElement forgetPasswordHintText;

    // 🔹 Step 2: Skip button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement skipButton;

    // 🔹 Step 3: Hint input field
    @AndroidFindBy(xpath = "//android.widget.EditText")
    private WebElement skipField;

    // 🔹 Step 4: Next button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
    private WebElement nextButton;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]")
    private static WebElement backButton;

    // 🔹 Constructor
    public OptionalHintScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate title
    public boolean isOptionalHintTitleDisplayed() {
        return optionalHintTitle.isDisplayed();
    }

    public boolean isForgetPasswordHintDisplayed() {
        try {
            return forgetPasswordHintText.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isNextButtonEnabled() {
        return nextButton.isEnabled();
    }


    // 🔹 Step 2: Enter hint text
    public void enterHintText() throws InterruptedException {
        Thread.sleep(500);
        skipField.click();
        Thread.sleep(300);
        skipField.clear();
        Thread.sleep(300);
        skipField.sendKeys("Test@123");
        Thread.sleep(500);
        System.out.println("✅ Hint text entered: Test@123");
    }

    // 🔹 Step 3: Skip Hint
    public void clickSkipButton() throws InterruptedException {
        Thread.sleep(1000);
        skipButton.click();
        Thread.sleep(1000);
    }

    // 🔹 Step 4: Click Next button
    public void clickNextButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement nextBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[4]")
        ));
        nextBtn.click();
        System.out.println("✅ Next button clicked on Optional Hint Screen");
    }

    // Click action for back button
    public static void clickBackButton() {
        backButton.click();
    }
}
