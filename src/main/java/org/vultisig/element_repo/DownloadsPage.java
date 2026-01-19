package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DownloadsPage extends BaseScreen {

    // 🔹 Downloads page title
    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='Downloads'])[2]")
    private WebElement downloadsTitle;

    // 🔹 File saving text field
    @AndroidFindBy(xpath = "//android.widget.EditText[@resource-id='android:id/title']")
    private WebElement fileSavingField;

    // 🔹 Save button
    @AndroidFindBy(xpath = "//android.widget.Button[@resource-id='android:id/button1']")
    private WebElement saveButton;

    // 🔹 Constructor
    public DownloadsPage(AppiumDriver driver) {
        super(driver);
    }

    // 🔹 Validate Downloads page is displayed
    public boolean isDownloadsPageDisplayed(AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(downloadsTitle));
        return downloadsTitle.isDisplayed();
    }

    // 🔹 Validate vault name is present in file saving field
    public boolean isVaultNamePresent(String vaultName, AppiumDriver driver) {
        WebDriverWait wait = new WebDriverWait(BaseScreen.driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(fileSavingField));

        String fieldText = fileSavingField.getText();
        return fieldText.contains(vaultName);
    }

    // 🔹 Click Save button
    public void clickSaveButton() {
        saveButton.click();
    }
}
