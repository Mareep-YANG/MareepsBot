package org.mareep.command;

import org.mareep.EventListener;
import org.mareep.EventManager;
import org.mareep.Main;
import org.mareep.annotations.EventHandler;
import org.mareep.entity.Sender;
import org.mareep.events.CommandEvent;
import org.mareep.events.GroupMessageEvent;

public class CommandListener implements EventListener {
    public long group_id;
    public CommandListener() {


    }

    public CommandListener(long group_id) {

        this.group_id = group_id;
        Main.eventManager.registerListener(this);
    }


    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        if (event.group_id == group_id) {
            if (event.raw_message.startsWith("/")) {
                Sender sender = event.sender;
                //为指令
                String allcommand = event.raw_message.substring(1);
                String[] args = allcommand.split(" ");
                String label = args[0];
                Main.eventManager.fireEvent(new CommandEvent(sender, label, args,event.group_id));
            }
        }
    }
}