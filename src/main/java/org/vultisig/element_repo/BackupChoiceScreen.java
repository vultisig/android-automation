package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BackupChoiceScreen extends BaseScreen {

    // 🔹 "Backup without password" text
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Backup without password']")
    private WebElement backupWithoutPasswordText;

    // 🔹 "Backup Without Password" → Continue button
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[5]")
    private WebElement continueButton;

    // 🔹 "Backup With Password" button
    @AndroidFindBy(xpath = "//android.widget.ScrollView/android.view.View[6]")
    private WebElement backupWithPasswordButton;

    // 🔹 Constructor
    public BackupChoiceScreen(AppiumDriver driver) {
        super(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // ✅ Validate if "Backup without password" text is displayed
    public boolean isBackupWithoutPasswordDisplayed() {
        return backupWithoutPasswordText.isDisplayed();
    }

    // ✅ Click the Continue button (Backup Without Password)
    public void clickContinueButton() {
        continueButton.click();
        System.out.println("✅ Continue button clicked (Backup Without Password)");
    }

    // ✅ Click the "Backup With Password" button
    public void clickBackupWithPasswordButton() {
        backupWithPasswordButton.click();
        System.out.println("✅ 'Backup With Password' button clicked");
    }
}
