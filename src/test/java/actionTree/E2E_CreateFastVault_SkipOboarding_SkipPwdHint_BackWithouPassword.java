package actionTree;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.vultisig.element_repo.OnboardingScreen2;
import org.vultisig.element_repo.StartScreen;
import tests.BaseTest;
import utils.DriverUtils;

import java.util.logging.Logger;

public class E2E_CreateFastVault_SkipOboarding_SkipPwdHint_BackWithouPassword extends BaseTest {

    private static final Logger logger = Logger.getLogger(E2E_CreateFastVault_SkipOboarding_SkipPwdHint_BackWithouPassword.class.getName());

    @Test(description = "Fast Vault E2E ")
    public void validateWelcomeScreen() throws InterruptedException {
        logger.info("🚀 Launching app and validating Welcome screen...");

        // Pass driver from DriverUtils
        StartScreen startScreen = new StartScreen(DriverUtils.getDriver());
        OnboardingScreen2 onboardingScreen2 = new OnboardingScreen2(DriverUtils.getDriver());
        Assert.assertTrue(
                startScreen.isVultisigLogoDisplayed(),
                "❌ Vultisig logo should be visible on Welcome Screen"
        );
        startScreen.clickCreateNewVaultButton();
        onboardingScreen2.clickSkipButton();
        Thread.sleep(10000);

        logger.info("✅ Welcome screen validated successfully!");
    }
}
