package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

public class VaultNameScreen extends BaseScreen {

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]")
    private static WebElement backButton;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Name your vault\"]")
    private static WebElement nameYourVaultTitle;

    // 🔹 Text: "You can always rename your vault later in the settings"
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"You can always rename your vault later in the settings\"]")
    private static WebElement renameVaultInfoText;

    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.view.View")
    private static WebElement clearTextButton;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private static WebElement vaultTextField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Next\"]/parent::*")
    private static WebElement continueButton;

    // 🔹 Store the last vault name entered
    private static String lastVaultName;

    public VaultNameScreen(AppiumDriver driver) {
        super(driver);
    }

    public static boolean isNameYourVaultDisplayed() {
        return nameYourVaultTitle.isDisplayed();
    }

    // Click action for back button
    public static void clickBackButton() {
        backButton.click();
    }

    public static boolean isRenameVaultInfoTextDisplayed() {
        return renameVaultInfoText.isDisplayed();
    }

    public static void clickClearTextButton() {
        clearTextButton.click();
    }

    // 🔹 Enter vault name and save it
    public static void enterVaultName(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(vaultTextField));

        vaultTextField.click();
        vaultTextField.clear();

        Random random = new Random();
        int randomNum = 100 + random.nextInt(900);
        lastVaultName = "Fast Vault #" + randomNum;

        vaultTextField.sendKeys(lastVaultName);
    }

    public static void clickContinueButton() {
        continueButton.click();
    }

    // Check if it's enabled
    public static boolean isContinueButtonEnabled() {
        return continueButton.isEnabled();
    }

    // 🔹 Getter for last vault name
    public static String getLastVaultName() {
        return lastVaultName;
    }
}
