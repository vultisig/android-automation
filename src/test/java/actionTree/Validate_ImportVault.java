package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.ImportVaultFlowScreen;
import org.vultisig.element_repo.StartScreen;
import org.vultisig.element_repo.StartScreen;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class Validate_ImportVault extends BaseTest {

    private static final Logger logger = Logger.getLogger(Validate_ImportVault.class.getName());

    @Test(description = "Validate the Import Vault button and its functionality.")
    public void validateImportVaultButton() throws InterruptedException {

        logger.info("🚀 Starting ImportVaultTest...");

        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        ImportVaultFlowScreen importScreen = new ImportVaultFlowScreen(DriverUtils.getDriver());

        // Validate Vultisig logo on Start Screen
        Assert.assertTrue(
                startScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Start Screen"
        );
        startScreen.assertVultisigName();

        // Click the Import Vault button
        startScreen.clickImportVaultButton();

        // Validate Import title
        Assert.assertTrue(importScreen.isImportTitleDisplayed(), "❌ 'Import' title not displayed");

        // Click back button
        importScreen.clickBackAndExit();
        Thread.sleep(4000);

        // Quit driver
        DriverUtils.getDriver().quit();
        logger.info("✅ Driver quit successfully after ImportVaultTest.");
    }
}
