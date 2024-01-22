package org.mareep;

import com.alibaba.fastjson.JSONObject;
import org.mareep.client.wsClient;
import org.mareep.command.CommandListener;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static String uri = "";
    public static HashMap<String , JSONObject> EchoPool = new HashMap<>()  ;
    public static EventManager eventManager;
    public static wsClient client;

    public static void init(String cfgPath) throws URISyntaxException {
        ConfigurationManager configManager;
        //自动创建配置文件
        if (!new File(cfgPath).exists()) {
            try {
                new File(cfgPath).createNewFile();
                configManager = new ConfigurationManager(cfgPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            configManager = new ConfigurationManager(cfgPath);
        }
        // mode 为 WebSocket , url -> WebSocket的url

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
    }
}