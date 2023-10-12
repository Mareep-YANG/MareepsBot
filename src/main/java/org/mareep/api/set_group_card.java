package org.mareep.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.Main;
import org.mareep.entity.Message;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class set_group_card {
    public String action = "set_group_card";
    public String group_id;
    public String user_id;
    public String card;
    public String echo;
    public set_group_card( String group_id, String user_id,String card) {
        this.group_id = group_id;
        this.card = card;
        this.user_id = user_id;

    }

    public void set_group_card() {
        Params params;
        params = Params.builder()
                .group_id(group_id)
                .user_id(user_id)
                .card(card)

                .build();



        Request request = Request.builder()
                .action(action)
                .params(params)
                .build();
        String json = JSON.toJSONString(request);
        Main.client.send(json);
    }

}
