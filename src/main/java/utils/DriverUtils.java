package utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.Duration;

public class DriverUtils {

    private static AppiumDriver driver;

    public static void initializeDriver(URL appiumServerUrl) throws IOException {

        JSONObject jsonObj = new JSONObject(
                JsonReader.read("capabilities.json").toString()
        );

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("platformName", jsonObj.getString("platform"));
        caps.setCapability("appium:deviceName", jsonObj.getString("deviceName"));
        caps.setCapability("appium:automationName", jsonObj.getString("automationName"));
        caps.setCapability("appium:newCommandTimeout", 3600);
        caps.setCapability("appium:autoGrantPermissions", true);

        // ✅ Build APK path safely
        String appPath = Paths.get(
                System.getProperty("user.dir"),
                jsonObj.getString("app_url")
        ).toString();

        File apk = new File(appPath);
        if (!apk.exists()) {
            throw new RuntimeException("❌ APK not found at: " + apk.getAbsolutePath());
        }

        caps.setCapability("appium:app", apk.getAbsolutePath());

        driver = new AndroidDriver(appiumServerUrl, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println("✅ Driver initialized");
        System.out.println("📦 APK Path: " + apk.getAbsolutePath());
    }

    public static AppiumDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call initializeDriver() first.");
        }
        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
            System.out.println("🛑 Driver quit successfully");
        }
    }
}
