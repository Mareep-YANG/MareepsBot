package org.mareep.test;

import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.send_msg;
import org.mareep.api.set_group_ban;
import org.mareep.api.set_group_special_title;
import org.mareep.events.GroupMessageEvent;
import org.mareep.events.GroupPokeEvent;


public class MessageListener implements EventListener {

        @EventHandler
        public void onMessage(GroupMessageEvent event) {
                if (event.group_id == 337995320 ) {
                        if (event.raw_message.contains("原神") || event.raw_message.contains("op") ) {
                                send_msg send_msg = new send_msg("group", event.group_id, "原神,启动!", false);
                                send_msg.send_msg();
                        }
                        if (event.raw_message.equals("自闭套餐")){
                                System.out.println(event.group_id + "畅享自闭套餐");
                                set_group_ban set_group_ban = new set_group_ban(event.group_id, event.user_id, 60);
                                set_group_ban.setBan();
                        }
                        if (event.raw_message.equals("精致睡眠")){
                                System.out.println(event.group_id + "畅享精致睡眠");
                                set_group_ban set_group_ban = new set_group_ban(event.group_id, event.user_id, 28800);
                                set_group_ban.setBan();
                        }

                }

        }
}
