package org.mareep.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.Main;
import org.mareep.entity.Message;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class send_msg {
    public String action = "send_msg";
    public String message_type;
    public long id;
    public String message;
    public boolean auto_escape;
    public String echo;
    public send_msg(String message_type, long id, String message, boolean auto_escape) {
        this.message_type = message_type;
        this.id = id;
        this.message = message;
        this.auto_escape = auto_escape;

    }

    public void send_msg() {
        Params params;
        if (message_type.equals("group")){
             params = Params.builder()
                    .message_type(message_type)
                    .group_id(id)
                    .message(message)
                    .auto_escape(auto_escape)
                    .build();
        }
        else {
             params = Params.builder()
                    .message_type(message_type)
                    .user_id(id)
                    .message(message)
                    .auto_escape(auto_escape)
                    .build();
        }


        Request request = Request.builder()
                .action(action)
                .params(params)
                .build();
        String json = JSON.toJSONString(request);
        Main.client.send(json);
    }

}
