package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import java.util.logging.Logger;

public class ScanQRScreen extends BaseScreen {

    private static final Logger logger = Logger.getLogger(ScanQRScreen.class.getName());


    // 🔹 "Scan QR Code" title
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Scan QR Code']")
    private WebElement scanQRCodeTitle;

    // 🔹 Back button
    @AndroidFindBy(xpath = "//z1.q0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.view.View[1]")
    private WebElement backButton;

    // 🔹 Constructor
    public ScanQRScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Validate if "Scan QR Code" title is displayed
    public boolean isScanQRCodeDisplayed() {
        return scanQRCodeTitle.isDisplayed();
    }

    // 🔹 Click Back button and exit driver
    public void clickBackAndExit() {
        backButton.click();
        System.out.println("✅ Clicked back and exited the driver successfully");
    }
}
