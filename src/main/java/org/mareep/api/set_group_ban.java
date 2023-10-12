package org.mareep.api;

import com.alibaba.fastjson.JSON;
import org.mareep.Main;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class set_group_ban {
    public String action = "set_group_ban";
    public long group_id;
    public long user_id;
    public long duration;
    public String echo;
    public set_group_ban(long group_id, long user_id, long duration) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.duration = duration;
    }
    public void setBan() {
        Params params = Params.builder()
                .group_id(group_id)
                .user_id(user_id)
                .duration(duration)
                .build();
        Request request = Request.builder()
                .action(action)
                .params(params)
                .build();
        String json = JSON.toJSONString(request);
        Main.client.send(json);
    }

}
