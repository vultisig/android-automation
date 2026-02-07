package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * SetupScreen - formatted and using java.util.logging.Logger for consistent logging.
 * No logic changes made — only formatting and logger substitution for System.out.println.
 */
public class SetupScreen extends BaseScreen {

    private static final Logger logger = Logger.getLogger(SetupScreen.class.getName());

    // 🔹 Back button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View[1]")
    private WebElement backButton;

    // 🔹 Info icon button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.view.View[3]")
    private WebElement infoButton;

    // 🔹  Fast Vault selection box
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[1]")
    private WebElement fastVaultBox;

    // 🔹  Continue button
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]")
    private WebElement continueButton;

    // Secure vault selection box
    @AndroidFindBy(xpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]")
    private WebElement secureVaultBox;

    // 🔹 Constructor
    public SetupScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Click Back Button
    public void clickBackButtonOnChooseSetup() {
        backButton.click();
        logger.info("✅ Back button clicked on Choose Setup screen");
    }

    // Click info icon
    public void clickInfoButton() {
        infoButton.click();
        logger.info("ℹ️  Info icon clicked on Choose Setup screen");
    }

    public boolean clickInfoAndReturnSimple() {
        try {
            if (driver == null) {
                logger.severe("❗ Driver is null. Aborting info link verification.");
                return false;
            }

            // Wait for info button clickable
            try {
                WebDriverWait pre = new WebDriverWait(driver, Duration.ofSeconds(8));
                pre.until(d -> {
                    try {
                        return infoButton != null && infoButton.isDisplayed() && infoButton.isEnabled();
                    } catch (Exception ex) {
                        return false;
                    }
                });
            } catch (Exception ignored) {
                logger.warning("Pre-check: infoButton not visible/clickable within pre-wait.");
            }

            // Capture app package from capabilities as best-effort
            Object capPkg = driver.getCapabilities().getCapability("appPackage");
            String appPackage = capPkg != null ? capPkg.toString() : null;
            logger.info("🔁 appPackage (caps): " + appPackage);

            // Click info icon
            try {
                infoButton.click();
                logger.info("ℹ️ Info icon clicked; waiting briefly for external app to open...");
            } catch (Exception e) {
                logger.severe("❌ Failed to click infoButton: " + e.getMessage());
                return false;
            }

            // Wait a bit for external app to launch (if any)
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            // Try to bring app back
            if (appPackage != null) {
                Map<String, Object> params = new HashMap<>();
                params.put("appId", appPackage);
                try {
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("mobile: activateApp", params);
                    logger.info("🔙 Called mobile: activateApp -> " + appPackage);
                } catch (Exception e) {
                    logger.warning("⚠️ activateApp failed: " + e.getMessage());
                }
            } else {
                logger.warning("⚠️ appPackage unknown; cannot activate app reliably.");
            }

            // Wait for Choose Setup screen stability (backButton visible)
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
            boolean returned = wait.until(d -> {
                try {
                    return backButton != null && backButton.isDisplayed();
                } catch (Exception ex) {
                    return false;
                }
            });

            if (returned) {
                logger.info("✅ Returned to Choose Setup screen successfully.");
                return true;
            } else {
                logger.severe("❌ Could not confirm Choose Setup screen after activateApp.");
                return false;
            }

        } catch (Exception e) {
            logger.severe("❗ Error in clickInfoAndReturnSimple: " + e.getMessage());
            return false;
        }
    }

    // ----------------- New assert-style validators with tolerant XPaths -----------------

    /**
     * Validates (and asserts) the Fast Vault texts are visible.
     * Uses exact text XPaths as provided. Throws AssertionError via TestNG Assert on failure.
     */
    public void validateFastVaultTextsAssert() {
        List<String> xpaths = Arrays.asList(
                "//android.widget.TextView[@text=\"Quick, 1 device setup\"]",
                "//android.widget.TextView[@text=\"Only 1 device needed\"]",
                "//android.widget.TextView[@text=\"Store funds for daily use\"]",
                "//android.widget.TextView[@text=\"Vultiserver co-signs instantly\"]"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        for (String xp : xpaths) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                logger.info("✅ Visible (fast-vault): " + xp);
            } catch (Exception e) {
                logger.severe("❌ Not visible (fast-vault): " + xp + " -> " + e.getMessage());
                Assert.fail("Fast Vault text not visible: " + xp);
            }
        }
    }

    /**
     * Clicks the Secure Vault selection box using the exact XPath provided by you.
     */
    public void clickSecureVaultByXpath() {
        String secureXpath = "//z1.s0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.view.View[1]/android.view.View[2]";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement secure = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(secureXpath)));
            secure.click();
            logger.info("✅ Clicked Secure Vault element: " + secureXpath);
        } catch (Exception e) {
            logger.severe("❌ Failed to click Secure Vault xpath: " + secureXpath + " -> " + e.getMessage());
            Assert.fail("Failed to click Secure Vault element: " + e.getMessage());
        }
    }

    /**
     * Validates (and asserts) the Ultimate / Secure texts are visible.
     * Uses exact text XPaths as provided. Throws AssertionError via TestNG Assert on failure.
     */
    public void validateUltimateSecurityTextsAssert() {
        List<String> xpaths = Arrays.asList(
                "//android.widget.TextView[@text=\"Ultimate security\"]",
                "//android.widget.TextView[@text=\"Maximum security with multiple devices\"]",
                "//android.widget.TextView[@text=\"Always accessible with device backups\"]",
                "//android.widget.TextView[@text=\"Secure any amount of assets\"]"
        );

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        for (String xp : xpaths) {
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xp)));
                logger.info("✅ Visible (ultimate-security): " + xp);
            } catch (Exception e) {
                logger.severe("❌ Not visible (ultimate-security): " + xp + " -> " + e.getMessage());
                Assert.fail("Ultimate/Secure text not visible: " + xp);
            }
        }
    }

    // ----------------- End new assert-style validators -----------------

    // 🔹  Click Fast Vault Box
    public void clickFastVaultBox() {
        fastVaultBox.click();
        logger.info("✅ Fast Vault option selected");
    }

    // 🔹  Verify Continue button visibility
    public boolean isContinueButtonDisplayed() {
        return continueButton.isDisplayed();
    }

    // 🔹  Click Continue button
    public void clickContinueButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueButton.click();
        logger.info("✅ Continue button clicked on Setup Screen");
    }

    // 🔹 Click Fast Vault Box
    public void clickSecureVaultBox() {
        secureVaultBox.click();
        logger.info("✅ Fast Vault option selected");
    }
}
