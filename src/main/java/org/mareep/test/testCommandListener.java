package org.mareep.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.*;
import org.mareep.events.CommandEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class testCommandListener implements EventListener {
    @EventHandler
    public void onCommand(CommandEvent evt) throws IOException {

        if (evt.label.equals("bot")){
            if (evt.args[1].equals("getData")){
                if (evt.args.length == 3){
                    new get_group_member_info(evt.group_id , evt.args[2] , false).get_group_member_info(new GetApi.ResponseCallback() {
                        @Override
                        public void onSuccess(JSONObject vaule) {
                            new send_msg("group",evt.group_id,vaule.toJSONString(), false).send_msg();
                        }

                        @Override
                        public void onFailure(String error) {
                            send_msg send_msg = new send_msg("group", evt.group_id, "获取失败", false);
                        }
                    });
                }
            }

        }





    }
}
