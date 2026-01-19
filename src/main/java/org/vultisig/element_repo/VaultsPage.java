package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class VaultsPage extends BaseScreen {

    // 🔹 Wallet element to wait for
    @AndroidFindBy(xpath = "//z1.q0/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[3]/android.view.View/android.view.View/android.view.View[1]")
    private WebElement walletElement;


    // 🔹 Constructor
    public VaultsPage(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Wait for wallet element to appear
    public boolean waitForWalletElement(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(25));
        wait.until(ExpectedConditions.visibilityOf(walletElement));
        System.out.println("✅ Wallet element is visible on Vaults Page");
        return walletElement.isDisplayed();
    }

    // 🔹 Click to view vaults
    public void clickViewVaults() {
        walletElement.click();
        System.out.println("✅ 'View Vaults' button clicked");
    }

    // 🔹 Validate vault name in the list dynamically
    public boolean isVaultNameDisplayed(String vaultName, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(10));
        String xpath = "//android.widget.TextView[contains(@text,'" + vaultName + "')]";
        WebElement vaultElement = wait.until(
                ExpectedConditions.visibilityOf(BaseScreen.driver.findElement(By.xpath(xpath)))
        );
        boolean displayed = vaultElement.isDisplayed();
        System.out.println("🔍 Vault name '" + vaultName + "' is displayed: " + displayed);
        return displayed;
    }
}
