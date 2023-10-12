package org.mareep.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.mareep.Main;
import org.mareep.entity.Sender;
import org.mareep.events.GroupMessageEvent;
import org.mareep.events.GroupPokeEvent;

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


        JSONObject jsonObject = JSON.parseObject(message); // 把收到信息转为Json对象
        int time = 0;int self_id = 0;String post_type = ""; // 三个字段
        //
        if (jsonObject.containsKey("time")){
            time = jsonObject.getInteger("time");
        }
        if (jsonObject.containsKey("self_id")){
            self_id = jsonObject.getInteger("self_id");
        }
        if (jsonObject.containsKey("post_type")){
            post_type = jsonObject.getString("post_type");
        }
        //echo
        if (jsonObject.containsKey("status")){
            // 本信息是一个回执
            String status = jsonObject.getString("status");
            //回执状态为OK
            if (status.equals("ok")){
                if (jsonObject.containsKey("echo")){ // 对应请求是get
                    Main.EchoPool.put(jsonObject.getString("echo"),jsonObject);
                }
            }
        }
        //
        //message
        if (post_type!= null && post_type.equals("message")){
            String message_type = jsonObject.getString("message_type");
            //groupMessageEvent
            if (message_type.equals("group")){
                long group_id = jsonObject.getLong("group_id");
                long user_id = jsonObject.getLong("user_id");
                String raw_message = jsonObject.getString("raw_message");
                int font = jsonObject.getInteger("font");
                Sender sender = jsonObject.getObject("sender",Sender.class);
                GroupMessageEvent groupMessageEvent = new GroupMessageEvent(user_id,raw_message,font,group_id,sender);
                groupMessageEvent.time = time;
                groupMessageEvent.post_type = post_type;
                groupMessageEvent.self_id = self_id;
                Main.eventManager.fireEvent(groupMessageEvent);
            }
            //
        }
        //notice
        if (post_type!=null && post_type.equals("notice")){
            String notice_type = jsonObject.getString("notice_type");
            //notify
            if (notice_type.equals("notify")){
                String sub_type = jsonObject.getString("sub_type");
                //groupPokeEvent
//                if (sub_type.equals("poke")){
//                    int group_id = jsonObject.getInteger("group_id");
//                    int user_id = jsonObject.getInteger("user_id");
//                    GroupPokeEvent groupPokeEvent = new GroupPokeEvent(user_id,group_id);
//                    groupPokeEvent.time = time;
//                    groupPokeEvent.post_type = post_type;
//                    groupPokeEvent.self_id = self_id;
//                    Main.eventManager.fireEvent(groupPokeEvent);
//                }
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
        ex.printStackTrace();
    }

}
