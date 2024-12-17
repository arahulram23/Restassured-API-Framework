package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    Properties prop;

    public void readConfigFile() {
        try {
            FileInputStream fileInputStream = new FileInputStream("./config.properties");
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getBaseUrl(){
        return prop.getProperty("base.url");

    }
}
