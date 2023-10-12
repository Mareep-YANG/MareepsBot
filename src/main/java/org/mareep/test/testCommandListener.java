package org.mareep.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.send_msg;
import org.mareep.api.set_group_ban;
import org.mareep.api.set_group_card;
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
            if (evt.args[1].equals("ban")){
                new set_group_ban(337995320,evt.sender.getUser_id(),300).setBan();
            }
            if (evt.args[1].equals("nick")){
                if (evt.args.length > 2){
                    new send_msg("group", evt.group_id, "将用户"+evt.sender.getNickname()+"群名片改为"+ evt.args[2], false).send_msg();
                    new set_group_card(337995320,evt.sender.getUser_id(),evt.args[2]).set_group_card();
                }

            }
        }





    }
}
