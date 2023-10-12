package org.mareep.api;

import com.alibaba.fastjson.JSON;
import org.mareep.Main;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class set_group_special_title {
    public String action = "set_group_special_title";
    public long group_id;
    public long user_id;
    public String special_title;
    public long duration;
    public String echo;
    public set_group_special_title (long group_id, long user_id, String special_title, long duration) {
        this.group_id = group_id;
        this.user_id = user_id;
        this.special_title = special_title;
        this.duration = duration;
    }
    public void setSpecial_title() {
        Params params = Params.builder()
                .group_id(group_id)
                .user_id(user_id)
                .special_title(special_title)
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
