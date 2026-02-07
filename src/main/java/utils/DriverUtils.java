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
       // caps.setCapability("appium:noReset", true);
        //caps.setCapability("appium:fullReset", false);
       // caps.setCapability("appium:dontStopAppOnReset", true);



        // ✅ Build APK path safely
        // 📦 Auto-detect latest APK inside apps/android folder
        File apkDir = Paths.get(System.getProperty("user.dir"), "apps", "android").toFile();

        File[] apks = apkDir.listFiles((dir, name) -> name.toLowerCase().endsWith(".apk"));

        if (apks == null || apks.length == 0) {
            throw new RuntimeException("❌ No APK files found in: " + apkDir.getAbsolutePath());
        }

        File latestApk = java.util.Arrays.stream(apks)
                .max(java.util.Comparator.comparingLong(File::lastModified))
                .orElseThrow(() -> new RuntimeException("❌ Could not determine latest APK"));

        caps.setCapability("appium:app", latestApk.getAbsolutePath());

        System.out.println("📦 Using latest APK: " + latestApk.getName());


        driver = new AndroidDriver(appiumServerUrl, caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        System.out.println("✅ Driver initialized");
        System.out.println("📦 APK Path: " + latestApk.getAbsolutePath());
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
