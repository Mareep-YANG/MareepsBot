package org.mareep.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.Main;
import org.mareep.entity.Message;
import org.mareep.entity.MessageData;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class send_msg {
    public String action = "send_msg";
    public String message_type;
    public String id;
    public String message;
    public Message[] msg_json;
    public boolean auto_escape;
    public String echo;
    private boolean isText = true;

    public send_msg(String message_type, String id, String message, boolean auto_escape) {
        this.message_type = message_type;
        this.id = id;
        this.message = message;
        this.auto_escape = auto_escape;

    }

    public send_msg(String message_type, String id, Message msg_json[]) {
        this.message_type = message_type;
        this.id = id;
        this.msg_json = msg_json;
        this.isText = false;
    }

    public void send_msg() {
        Params params;
        if (isText) {
            Message[] msg = new Message[]{Message.builder().type("text").data(MessageData.builder().text(message).build()).build()};
            if (message_type.equals("group")) {
                params = Params.builder()
                        .message_type(message_type)
                        .group_id(Long.parseLong(id))
                        .message(msg)
                        .auto_escape(auto_escape)
                        .build();
            } else {
                params = Params.builder()
                        .message_type(message_type)
                        .user_id(Long.parseLong(id))
                        .message(msg)
                        .auto_escape(auto_escape)
                        .build();
            }
        } else {
            if (message_type.equals("group")) {
                params = Params.builder()
                        .message_type(message_type)
                        .group_id(Long.parseLong(id))
                        .message(msg_json)
                        .build();
            } else {
                params = Params.builder()
                        .message_type(message_type)
                        .user_id(Long.parseLong(id))
                        .message(msg_json)
                        .build();
            }
        }

        Request request = Request.builder()
                .action(action)
                .params(params)
                .build();
        String json = JSON.toJSONString(request);
        Main.client.send(json);
    }

}
