package org.mareep.onebot.api;

import com.alibaba.fastjson.JSONObject;
import org.mareep.onebot.entity.GroupMemberInfo;
import org.mareep.onebot.entity.Params;
import org.mareep.onebot.entity.Request;

public class get_group_member_info extends GetApi{
    public String action = "get_group_member_info";
    public String group_id;
    public String user_id;
    public boolean no_cache;
    public get_group_member_info(String group_id, String user_id, boolean no_cache){
        this.group_id = group_id;
        this.user_id = user_id;
        this.no_cache = no_cache;
    }

    public interface ResponseCallback_G {
        void onSuccess(GroupMemberInfo vaule);

        void onFailure(String error);
    }

    public void get_group_member_info(ResponseCallback_G callback) {
        Params params = Params.builder()
                .group_id(Long.parseLong(group_id))
                .user_id(Long.parseLong(user_id))
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
                JSONObject data = vaule.getJSONObject("data");
                GroupMemberInfo res = GroupMemberInfo.builder()
                        .group_id(data.getString("group_id"))
                        .age(data.getInteger("age"))
                        .area(data.getString("area"))
                        .last_sent_time(data.getString("last_sent_time"))
                        .unfriendly(data.getBoolean("unfriendly"))
                        .role(data.getString("role"))
                        .level(data.getString("level"))
                        .sex(data.getString("sex"))
                        .title_expire_time(data.getLong("title_expire_time"))
                        .card_changeable(data.getBoolean("card_changeable"))
                        .title(data.getString("title"))
                        .join_time(data.getString("join_time"))
                        .user_id(data.getString("user_id"))
                        .nickname(data.getString("nickname"))
//                        .shut_up_timestamp(data.getLong("shut_up_timestamp"))
                        .card(data.getString("card"))
                        .build();
                callback.onSuccess(res);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }
}
