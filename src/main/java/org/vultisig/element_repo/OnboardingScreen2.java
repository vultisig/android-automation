package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class OnboardingScreen2 extends BaseScreen {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Say hello to vault shares, your new recovery method']")
    private WebElement vaultShareText;

    // 🔹 Step 2: Back button
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Back']")
    private WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip']")
    private WebElement skipButton;


    // 🔹 Constructor
    public OnboardingScreen2(AppiumDriver driver) {
        super(driver); // calls BaseScreen’s constructor
    }

    // 🔹 Validate if the vault share text is displayed
    public boolean isVaultShareTextDisplayed() {
        return vaultShareText.isDisplayed();
    }

    // 🔹 Click on the back button
    public void clickBackButton() {
        backButton.click();
        System.out.println("✅ Back button clicked on Vault Share Screen");
    }

    // 🔹 Actions
    public boolean isSkipButtonDisplayed() {
        return skipButton.isDisplayed();
    }
    public void clickSkipButton() {
        skipButton.click();
    }
}
