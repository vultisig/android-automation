package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;

import java.time.Duration;
import java.util.logging.Logger;

public class StartScreen extends BaseScreen {

    // 🔹 Locators
    @AndroidFindBy(accessibility = "vultisig")
    private WebElement vultisigLogo;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Vultisig']")
    private WebElement vultisigName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create new vault']")
    private WebElement createNewVaultBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OR']")
    private WebElement orTxt;

    // 🔹 Scan QR button
    @AndroidFindBy(xpath = "//z1.q0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement scanQRButton;

    // 🔹 Import Vault button
    @AndroidFindBy(xpath = "//z1.q0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement importVaultButton;

    private static final Logger logger = Logger.getLogger(ScanQRScreen.class.getName());

    // 🔹 Constructor
    public StartScreen(AppiumDriver driver) {
        super(driver); // calls BaseScreen’s constructor
    }

    // 🔹 Actions
    public boolean isVultisigLogoDisplayed() {
        return waitForVisibility(vultisigLogo).isDisplayed();
    }

    public void assertVultisigName() {
        Assert.assertEquals(getText(vultisigName), "Vultisig", "❌ The text on the welcome screen does not match the expected 'Vultisig'");
    }

    public void clickCreateNewVaultButton() {
        createNewVaultBtn.click();
    }

    public boolean isORDisplayed(){
        return waitForVisibility(orTxt).isDisplayed();
    }

    // 🔹 Click Scan QR button
    public void clickScanQRButton() {
        scanQRButton.click();
        logger.info("✅ 'Scan QR' button clicked successfully!");
    }

    // 🔹 Click on Import Vault button
    public void clickImportVaultButton() {
        importVaultButton.click();
        System.out.println("✅ Import Vault button clicked successfully");
    }
}
