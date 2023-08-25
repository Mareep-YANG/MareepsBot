package org.mareep.events;

import com.alibaba.fastjson.JSONObject;

public class GroupMessageEvent extends Event{
    public int user_id;
    public JSONObject message;
    public String raw_message;
    public int font;
    public int group_id;


    public GroupMessageEvent(int user_id,  String raw_message, int font, int group_id) {
        this.user_id = user_id;
        this.raw_message = raw_message;
        this.font = font;
        this.group_id = group_id;
    }
}
