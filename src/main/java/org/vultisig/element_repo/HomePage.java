package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends BaseScreen {

    // 🔹 Wallet element to wait for
    @AndroidFindBy(xpath = "//android.view.View[@content-desc=\"vault type logo\"]/parent::*")
    private WebElement walletElement;

    // 🔹 Settings button on Home Page
    @AndroidFindBy(
            xpath = "//android.view.View[@content-desc=\"vault type logo\"]/parent::*/following-sibling::android.view.View[1]"
    )
    private WebElement settingsButton;


    // 🔹 Constructor
    public HomePage(AppiumDriver driver) {
        super(driver);
    }

    // -----------------VaultsPage-------------

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

    //----------------SettingsPage-----------
    // 🔹 Click Settings button
    public void clickSettingsButton() {
        settingsButton.click();
        System.out.println("✅ Settings button clicked");
    }
}
