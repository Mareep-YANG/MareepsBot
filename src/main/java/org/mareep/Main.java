package org.mareep;

import com.sun.net.httpserver.HttpServer;
import org.mareep.client.wsClient;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class Main {
    public static String uri = "";
    public static void main(String[] args) throws URISyntaxException {
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
            configManager.setProperty("mode", "1");
        }
        if (url == null){
            configManager.setProperty("url", "ws://0.0.0.0:8080");
        }
           //启动
        uri = url;
        if (configManager.getProperty("mode").equals("0")) {

        } else {
            URI serverUri = new URI(uri); // 建立连接
            wsClient client = new wsClient(serverUri);
            client.connect();
        }



    }
}