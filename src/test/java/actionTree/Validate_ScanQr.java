package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.ScanQRScreen;
import org.vultisig.element_repo.StartScreen;
import tests.BaseTest;
import utils.DriverUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import java.util.logging.Logger;

public class Validate_ScanQr extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_ScanQr.class.getName());

    @Test(description = "Validate the Scan QR button and its screen.")
    public void validateWelcomeScreen() throws InterruptedException {

        logger.info("🚀 Starting ScanQRTest...");

        StartScreen welcomeScreen = new StartScreen(DriverUtils.getDriver());
        ScanQRScreen scanQRScreen = new ScanQRScreen(DriverUtils.getDriver());

        // Validate Vultisig logo on Welcome Screen
        Assert.assertTrue(
                welcomeScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Welcome Screen"
        );
        welcomeScreen.assertVultisigName();

        // Click the Scan QR button
        welcomeScreen.clickScanQRButton();
        System.out.println("✅ Scan QR button clicked successfully.");

        // Validate title is displayed
        Assert.assertTrue(
                scanQRScreen.isScanQRCodeDisplayed(),
                "❌ 'Scan QR Code' title not displayed."
        );

        // Click Back button and exit driver
        scanQRScreen.clickBackAndExit();

        // Verify redirection back to Start Screen
        Assert.assertTrue(
                welcomeScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Welcome Screen"
        );
        welcomeScreen.assertVultisigName();

        // Quit driver
        DriverUtils.getDriver().quit();
    }
}
