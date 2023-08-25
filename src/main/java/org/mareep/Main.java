package org.mareep;

import org.mareep.client.wsClient;
import org.mareep.test.MessageListener;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static String uri = "";
    public static EventManager eventManager;
    public static wsClient client;
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
        // mode 为 WebSocket , url -> http或WebSocket的url

        String url = configManager.getProperty("url");
        if (url == null){
            configManager.setProperty("url", "ws://0.0.0.0:8080");
        }
           //启动
        uri = url;
        URI serverUri = new URI(uri); // 建立连接
        client = new wsClient(serverUri);
        client.connect();
        //事件管理系统
         eventManager = new EventManager();
        eventManager.registerListener(new MessageListener());





    }
}