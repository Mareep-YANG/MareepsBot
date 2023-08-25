package org.mareep.test;

import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.events.GroupMessageEvent;


public class MessageListener implements EventListener {

        @EventHandler
        public void onMessage(GroupMessageEvent event) {

        }
}
