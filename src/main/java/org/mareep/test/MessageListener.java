package org.mareep.test;

import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.send_msg;
import org.mareep.events.GroupMessageEvent;


public class MessageListener implements EventListener {

        @EventHandler
        public void onMessage(GroupMessageEvent event) {
                if (event.group_id == 337995320) {
                        if (event.raw_message.contains("原神")) {
                                send_msg send_msg = new send_msg("group", event.group_id, "原神,启动!", false);
                                send_msg.send_msg();
                        }
                }
                if (event.group_id == 1029427187) {
                        if (event.raw_message.contains("AC")) {
                                send_msg send_msg = new send_msg("group", event.group_id, "玩你妈AC , 玩原神去", false);
                                send_msg.send_msg();
                        }
                }
        }
}
