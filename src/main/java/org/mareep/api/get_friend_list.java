package org.mareep.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.mareep.entity.GroupMemberInfo;
import org.mareep.entity.Params;
import org.mareep.entity.Request;

public class get_friend_list extends GetApi{
    public String action = "get_friend_list";
    public get_friend_list(){}
    public interface ResponseCallback_G {
        void onSuccess(JSONArray vaule);

        void onFailure(String error);
    }

    public void get_friend_list(get_friend_list.ResponseCallback_G callback) {
        Params params = Params.builder()
                .build();
        Request request = Request.builder()
                .action(action)
                .params(params)
                .echo(echo)
                .build();
        SentRequest(request, new GetApi.ResponseCallback() {
            @Override
            public void onSuccess(JSONObject vaule) {

                JSONArray data = vaule.getJSONArray("data");
                callback.onSuccess(data);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

}
