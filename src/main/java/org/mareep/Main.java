package org.mareep;

import com.alibaba.fastjson.JSONObject;
import org.mareep.client.wsClient;
import org.mareep.command.CommandListener;
import org.mareep.test.testCommandListener;

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
        List<String> group_id = new ArrayList<String>() {
            {
                add("833217638");
                add("337995320");
                add("859562984");
                add("663520905");
            }
        };

        new CommandListener(group_id);
        eventManager.registerListener(new testCommandListener());

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String stringValue = scanner.next();
            if (stringValue.equalsIgnoreCase("exit")) {
                client.close();
                System.exit(0);
            }
        }

    }
}