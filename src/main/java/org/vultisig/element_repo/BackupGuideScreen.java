package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BackupGuideScreen extends BaseScreen {


    // 🔹 Checkbox
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"I have read and understand what to do\"]/preceding-sibling::android.view.View")
    private WebElement checkBox;

    // 🔹 Button (wait for it to appear)
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Start using your Vault\"]/parent::*")
    private WebElement nextButton;

    // 🔹 Constructor
    public BackupGuideScreen(AppiumDriver driver) {
        super(driver);
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
