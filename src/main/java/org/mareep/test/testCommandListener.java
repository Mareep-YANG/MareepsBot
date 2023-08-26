package org.mareep.test;

import org.mareep.EventListener;
import org.mareep.annotations.EventHandler;
import org.mareep.api.send_msg;
import org.mareep.events.CommandEvent;

public class testCommandListener implements EventListener {
    @EventHandler
    public void onCommand(CommandEvent evt){

//        if (evt.label.equals("test")){
//            send_msg send_msg = new send_msg("group", evt.group_id, "test", false);
//            send_msg.send_msg();
//        }




        
    }
}
