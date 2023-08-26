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
                if (event.group_id == 337995320 || event.group_id == 859562984 ) {
                        if (event.raw_message.contains("原神") || event.raw_message.contains("op") ) {
                                send_msg send_msg = new send_msg("group", event.group_id, "原神,启动!", false);
                                send_msg.send_msg();
                        }

                }

        }
}
