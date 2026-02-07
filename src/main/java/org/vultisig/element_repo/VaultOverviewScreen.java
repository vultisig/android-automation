package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class VaultOverviewScreen extends BaseScreen {

    // 🔹 Step 1: Vault Overview title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Vault Overview\"]")
    private static WebElement vaultOverviewTitle;

    // 🔹 Step 2: Vault backup info
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Your vault holds 2 shares, back them up now\"]")
    private static WebElement backupInfoText;

    // 🔹 Step 2 button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View")
    private static WebElement backupButton;

    // 🔹 Step 3: Server holds part 1 info
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Part 1 of the vault shares will be held by the server.']")
    private static WebElement serverShareText;

    // 🔹 Step 4: Self-custody info
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='It is sent to you for complete self-custody! Check your e-mail to verify']")
    private static WebElement selfCustodyText;

    // 🔹 Constructor
    public VaultOverviewScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Step 1: Validate Vault Overview title
    public static boolean isVaultOverviewTitleDisplayed() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(25));
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//android.widget.TextView[@text='Vault Overview']")));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // 🔹 Step 2: Validate backup info
    public static boolean isBackupInfoDisplayed() {
        return backupInfoText.isDisplayed();
    }

    // 🔹 Step 2: Click backup button
    public static void clickBackupButton() throws InterruptedException {
        Thread.sleep(1000);
        backupButton.click();
        Thread.sleep(1000);
    }

    // 🔹 Step 3: Validate server share info
    public static boolean isServerShareTextDisplayed() {
        return serverShareText.isDisplayed();
    }

    // 🔹 Step 3: Click server share next
    public static void clickServerShareNextButton() throws InterruptedException {
        Thread.sleep(1000);
        backupButton.click();  // same button xpath
        Thread.sleep(1000);
    }

    // 🔹 Step 4: Validate self-custody info
    public static boolean isSelfCustodyTextDisplayed() {
        return selfCustodyText.isDisplayed();
    }

    // 🔹 Step 4: Click self-custody next
    public static void clickSelfCustodyNextButton() throws InterruptedException {
        Thread.sleep(1000);
        backupButton.click();  // same button xpath
        Thread.sleep(1000);
    }
}
