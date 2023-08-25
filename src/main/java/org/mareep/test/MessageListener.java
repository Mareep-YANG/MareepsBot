package org.mareep.test;

import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.send_msg;
import org.mareep.events.GroupMessageEvent;


public class MessageListener implements EventListener {

        @EventHandler
        public void onMessage(GroupMessageEvent event) {
                if (event.group_id == 337995320 && event.raw_message.equals("test")) {
                        send_msg send_msg = new send_msg("group",  event.group_id, "测试成功", false);
                        send_msg.send_msg();
                }
        }
}
