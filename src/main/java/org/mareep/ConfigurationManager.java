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

    //从配置文件中读取配置 返回一个Properties对象
    private Properties loadProperties() {
        Properties props = new Properties();
        try (InputStream inputStream = new FileInputStream(configFilePath)) {
            props.load(inputStream);
        } catch (IOException e) {
            System.out.println("未找到配置文件.");
        }
        return props;
    }

    //通过键从配置文件中读取配置 返回一个String对象
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    //通过键和值向配置文件中写入配置
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        saveProperties();
    }

    //保存
    private void saveProperties() {
        try (OutputStream outputStream = new FileOutputStream(configFilePath)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            System.out.println("Error saving config.");
        }
    }
}
