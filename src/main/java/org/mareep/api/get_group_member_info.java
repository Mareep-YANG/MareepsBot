package org.mareep.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.Main;
import org.mareep.entity.GroupMemberInfo;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class get_group_member_info extends GetApi{
    public String action = "get_group_member_info";
    public String group_id;
    public String user_id;
    public JSONObject res = new JSONObject();
    public boolean no_cache;
    public get_group_member_info(String group_id, String user_id, boolean no_cache){
        this.group_id = group_id;
        this.user_id = user_id;
        this.no_cache = no_cache;
    }
    public void get_group_member_info(ResponseCallback callback) {
        Params params = Params.builder()
                .group_id(group_id)
                .user_id(user_id)
                .no_cache(no_cache)
                .build();
        Request request = Request.builder()
                .action(action)
                .params(params)
                .echo(echo)
                .build();
        SentRequest(request, new ResponseCallback() {
            @Override
            public void onSuccess(JSONObject vaule) {
                res = vaule;
                callback.onSuccess(vaule);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }
}
