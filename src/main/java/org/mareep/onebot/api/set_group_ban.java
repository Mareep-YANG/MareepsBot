package org.mareep.onebot.api;

import com.alibaba.fastjson.JSON;
import org.mareep.onebot.QQbot;
import org.mareep.onebot.entity.Params;
import org.mareep.onebot.entity.Request;

public class set_group_ban {
    public String action = "set_group_ban";
    public String group_id;
    public String user_id;
    public long duration;
    public String echo;
    public set_group_ban(String group_id, String user_id, long duration) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.duration = duration;
    }
    public void setBan() {
        Params params = Params.builder()
                .group_id(Long.parseLong(group_id))
                .user_id(Long.parseLong(user_id))
                .duration(duration)
                .build();
        Request request = Request.builder()
                .action(action)
                .params(params)
                .build();
        String json = JSON.toJSONString(request);
        QQbot.client.send(json);
    }

}
