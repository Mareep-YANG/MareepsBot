package org.mareep;

import java.io.*;
import java.util.Properties;

public class ConfigurationManager {
    private String configFilePath;
    private Properties properties;

    public ConfigurationManager(String configFilePath) {
        this.configFilePath = configFilePath;
        this.properties = loadProperties();
    }

    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream inputStream = new FileInputStream(configFilePath)) {
            props.load(inputStream);
        } catch (IOException e) {
            System.out.println("Config file not found. Creating a new one.");
        }
        return props;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    private void saveProperties() {
        try (OutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            System.out.println("Error saving config.");
        }
    }
}
