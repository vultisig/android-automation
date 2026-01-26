package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static utils.Constants.CONFIG_PROPERTY;

public class ConfigReader {
    private FileInputStream fileInputStream;
    private String filePath;
    private Properties props;

    public ConfigReader() {
        InputStream inputStream = DriverUtils.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTY);
        this.filePath = System.getProperty("user.dir")+"/src/test/resources/config.properties";
        this.loadConfig();
    }

    public void setProperty(String key, String value) {
        this.props.setProperty(key, value);
    }

    public String getProperty(String key) {
        return this.props.getProperty(key);
    }

    public void loadConfig() {
        try {
            this.fileInputStream = new FileInputStream(filePath);
            this.props = new Properties();
            this.props.load(this.fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}