package tests;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.DriverUtils;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

import java.io.File;
import java.io.IOException;

@Listeners({AllureTestNg.class})
public class BaseTest {
    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void beforeSuite() {
        String osName = System.getProperty("os.name").toLowerCase();
        String nodePath;
        String appiumMainJsPath;

        if (osName.contains("mac")) {
            nodePath = "/Users/rajababu/.nvm/versions/node/v20.19.1/bin/node";
            appiumMainJsPath = "/Users/rajababu/.nvm/versions/node/v20.19.1/lib/node_modules/appium/build/lib/main.js";
        } else if (osName.contains("win")) {
            nodePath = "C:\\Program Files\\nodejs\\node.exe";
            appiumMainJsPath = "C:\\Users\\vigne\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js";
        } else {
            throw new RuntimeException("Unsupported OS for Appium setup: " + osName);
        }

        service = new AppiumServiceBuilder()
                .withAppiumJS(new File(appiumMainJsPath))
                .usingDriverExecutable(new File(nodePath))
                .withArgument(() -> "--allow-cors")
                .usingAnyFreePort()
                .build();

        service.start();

        if (service == null || !service.isRunning()) {
            throw new RuntimeException("❌ Appium server failed to start");
        }

        System.out.println("✅ Appium server started on: " + service.getUrl());
    }

    @BeforeMethod
    public void setUp() throws IOException {
        DriverUtils.initializeDriver(service.getUrl());  // pass dynamic server URL
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverUtils.quitDriver();
    }

    @AfterSuite
    public void afterSuite() {
        if (service != null && service.isRunning()) {
            service.stop();
            System.out.println("🛑 Appium server stopped");
        }
    }
    @AfterMethod
    public void attachScreenshotOnFailure(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            saveScreenshotPNG();
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshotPNG() {
        return ((TakesScreenshot) DriverUtils.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }
}
