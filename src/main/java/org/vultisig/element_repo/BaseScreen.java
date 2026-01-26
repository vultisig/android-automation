package org.vultisig.element_repo;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverUtils;

import java.time.Duration;

public class BaseScreen {

    protected static AppiumDriver driver;
    private WebDriverWait wait;

    // ✅ Constructor that accepts driver
    public BaseScreen(AppiumDriver driver) {
        this.driver = driver != null ? driver : DriverUtils.getDriver();
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(15));
        PageFactory.initElements(new AppiumFieldDecorator(this.driver, Duration.ofSeconds(10)), this);
    }

    // ✅ Helper: wait for element
    protected WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    // ✅ Helper: safe click
    protected void click(WebElement element) {
        waitForVisibility(element).click();
    }

    // ✅ Helper: safe get text
    protected String getText(WebElement element) {
        return waitForVisibility(element).getText();
    }
}
