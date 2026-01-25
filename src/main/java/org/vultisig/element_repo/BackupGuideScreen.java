package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackupGuideScreen extends BaseScreen {

    // 🔹 Back-Up guide element
    @AndroidFindBy(xpath = "//android.view.TextureView")
    private WebElement backupGuideElement;

    // 🔹 Checkbox
    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View")
    private WebElement checkBox;

    // 🔹 Button (wait for it to appear)
    @AndroidFindBy(xpath = "//z1.r0/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[3]")
    private WebElement nextButton;

    // 🔹 Constructor
    public BackupGuideScreen(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Validate Back-Up guide element is displayed
    public boolean isBackupGuideDisplayed(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(backupGuideElement));
        return backupGuideElement.isDisplayed();
    }

    // 🔹 Click checkbox
    public void clickCheckBox() {
        checkBox.click();
    }

    // 🔹 Wait for button and click
    public void clickNextButton(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        nextButton.click();
    }
}
