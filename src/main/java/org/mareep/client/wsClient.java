package org.mareep.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.mareep.Main;
import org.mareep.events.GroupMessageEvent;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;

public class wsClient extends WebSocketClient {

    public wsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("go-cqhttp服务器已连接");
    }

    @Override
    public void onMessage(String message) {

//        GroupMessageEvent playerJoinEvent = new GroupMessageEvent();
//        playerJoinEvent.setPlayerName("Alice");
//
//        eventManager.fireEvent(playerJoinEvent);
        JSONObject jsonObject = JSON.parseObject(message);
        int time = jsonObject.getInteger("time");
        int self_id = jsonObject.getInteger("self_id");
        String post_type = jsonObject.getString("post_type");
        if (post_type.equals("message")){
            String message_type = jsonObject.getString("message_type");
            if (message_type.equals("group")){
                int group_id = jsonObject.getInteger("group_id");
                int user_id = jsonObject.getInteger("user_id");
                String raw_message = jsonObject.getString("raw_message");
                int font = jsonObject.getInteger("font");
                GroupMessageEvent groupMessageEvent = new GroupMessageEvent(user_id,raw_message,font,group_id);
                Main.eventManager.fireEvent(groupMessageEvent);
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("机器人连接已断开");
    }

    @Override
    public void onError(Exception ex) {
        System.out.println("错误 : " + ex.getMessage());
    }

}
