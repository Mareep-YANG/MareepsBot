package org.mareep.events;

import com.alibaba.fastjson.JSONObject;

public class GroupMessageEvent extends Event{
    public long user_id;
    public JSONObject message;
    public String raw_message;
    public int font;
    public long group_id;


    public GroupMessageEvent(long user_id,  String raw_message, int font, long group_id) {
        this.user_id = user_id;
        this.raw_message = raw_message;
        this.font = font;
        this.group_id = group_id;
    }
}
