package org.mareep.events;

import com.alibaba.fastjson.JSONObject;
import org.mareep.entity.Sender;

public class GroupMessageEvent extends Event{
    public String user_id;
    public JSONObject message;
    public String raw_message;
    public int font;
    public String group_id;
    public Sender sender;


    public GroupMessageEvent(String user_id,  String raw_message, int font, String group_id , Sender sender) {
        this.user_id = user_id;
        this.raw_message = raw_message;
        this.font = font;
        this.group_id = group_id;
        this.sender = sender;
    }
}
