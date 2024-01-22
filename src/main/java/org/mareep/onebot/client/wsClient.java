package org.mareep.onebot.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.mareep.onebot.QQbot;
import org.mareep.onebot.entity.Message;
import org.mareep.onebot.entity.MessageData;
import org.mareep.onebot.entity.Sender;
import org.mareep.onebot.events.GroupMessageEvent;

import java.net.URI;

public class wsClient extends WebSocketClient {

    public wsClient(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("WebSocket已连接");
    }

    @Override
    public void onMessage(String message) {
        JSONObject jsonObject = JSON.parseObject(message); // 把收到信息转为Json对象
        int time = 0;
        long self_id = 0;
        String post_type = ""; // 三个字段
        //
        if (jsonObject.containsKey("time")) {
            time = jsonObject.getInteger("time");
        }
        if (jsonObject.containsKey("self_id")) {
            self_id = jsonObject.getInteger("self_id");
        }
        if (jsonObject.containsKey("post_type")) {
            post_type = jsonObject.getString("post_type");
        }
        //echo
        if (jsonObject.containsKey("status")){
            // 本信息是一个回执
            String status = jsonObject.getString("status");
            //回执状态为OK
            if (status.equals("ok")){
                if (jsonObject.containsKey("echo")){ // 对应请求是get
                    QQbot.EchoPool.put(jsonObject.getString("echo"), jsonObject);
                }
            }
        }
        //
        //message
        if (post_type!= null && post_type.equals("message")){
            String message_type = jsonObject.getString("message_type");
            //groupMessageEvent
            if (message_type.equals("group")) {
                String group_id = jsonObject.getString("group_id"); // 群号
                String user_id = jsonObject.getString("user_id");// 用户名
                String raw_message = jsonObject.getString("raw_message");// 原始信息(拉格朗日下疑似为空字符串)
                int font = jsonObject.getInteger("font");// 字体
                Sender sender = jsonObject.getObject("sender", Sender.class); // 发送者
                // 开始解析Message
                JSONArray MessagesJSON = jsonObject.getJSONArray("message");
                Message[] messages = new Message[MessagesJSON.size()]; // 信息数组
                for (Object messageObj : MessagesJSON) {
                    JSONObject messageJSON = (JSONObject) messageObj;
                    String type = messageJSON.getString("type");
                    MessageData messageData = messageJSON.getObject("data", MessageData.class);
                    Message message1 = Message.builder().type(type).data(messageData).build();
                    messages[MessagesJSON.indexOf(messageObj)] = message1;
                }
                GroupMessageEvent groupMessageEvent = new GroupMessageEvent(user_id, raw_message, font, group_id, sender, messages);
                groupMessageEvent.time = time;
                groupMessageEvent.post_type = post_type;
                groupMessageEvent.self_id = self_id;
                QQbot.eventManager.fireEvent(groupMessageEvent);
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
