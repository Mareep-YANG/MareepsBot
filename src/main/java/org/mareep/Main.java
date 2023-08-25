package org.mareep;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ConfigurationManager configManager;
        //自动创建配置文件
        if (!new File("config.properties").exists()) {
            try {
                new File("config.properties").createNewFile();
                configManager = new ConfigurationManager("config.properties");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else {
             configManager = new ConfigurationManager("config.properties");
        }
        // mode -> 0为http 1为WebSocket , url -> http或WebSocket的url
        String username = configManager.getProperty("mode");
        String url = configManager.getProperty("url");
        if (username == null) {
            configManager.setProperty("mode", "0");
        }
        if (url == null){
            configManager.setProperty("url", "yoururl");
        }

    }
}