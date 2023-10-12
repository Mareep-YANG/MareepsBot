package org.mareep.test;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.*;
import org.mareep.events.GroupMessageEvent;
import org.mareep.events.GroupPokeEvent;

import static java.lang.Thread.sleep;


public class MessageListener implements EventListener {

        @EventHandler
        public void onMessage(GroupMessageEvent event) {
                if (event.group_id.equals("833217638") ) {
                        if (event.raw_message.contains("原神") || event.raw_message.contains("op") ) {
                                send_msg send_msg = new send_msg("group", event.group_id, "原神,启动!", false);
                                send_msg.send_msg();
                        }
                        if (event.raw_message.contains("子涵")){
                                new get_group_member_info("833217638" , "1956681558" , false).get_group_member_info(new GetApi.ResponseCallback() {
                                        @Override
                                        public void onSuccess(JSONObject vaule) {
                                                new send_msg("group","833217638",vaule.toJSONString(), false).send_msg();
                                        }

                                        @Override
                                        public void onFailure(String error) {
                                                send_msg send_msg = new send_msg("group", event.group_id, "获取失败", false);
                                        }
                                });

                        }

                }

        }
}
