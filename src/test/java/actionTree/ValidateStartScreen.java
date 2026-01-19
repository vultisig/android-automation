package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.StartScreen;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class ValidateStartScreen extends BaseTest {

    private static final Logger logger = Logger.getLogger(ValidateStartScreen.class.getName());

    @Test(description = "Validate that the Welcome Screen displays logo and name correctly")
    public void validateWelcomeScreen() throws InterruptedException {
        logger.info("🚀 Launching app and validating Welcome screen...");

        // Pass driver from DriverUtils
        StartScreen welcomeScreen = new StartScreen(DriverUtils.getDriver());
        Assert.assertTrue(
                welcomeScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Welcome Screen"
        );
        welcomeScreen.assertVultisigName();
        Thread.sleep(10000);

        logger.info("✅ Welcome screen validated successfully!");
    }

}
