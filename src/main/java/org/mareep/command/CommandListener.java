package org.mareep.command;

import org.mareep.EventListener;
import org.mareep.EventManager;
import org.mareep.Main;
import org.mareep.annotations.EventHandler;
import org.mareep.entity.Message;
import org.mareep.entity.Sender;
import org.mareep.events.CommandEvent;
import org.mareep.events.GroupMessageEvent;

import java.util.List;

public class CommandListener implements EventListener {
    public List<String> group_id;

    public CommandListener() {


    }

    public CommandListener(List<String> group_id) {

        this.group_id = group_id;
        Main.eventManager.registerListener(this);
    }


    @EventHandler
    public void onMessage(GroupMessageEvent event) {
        if (group_id.contains(event.group_id)) {
            if (getWholeText(event.messages).startsWith("/")) {
                Sender sender = event.sender;
                //为指令
                String allcommand = getWholeText(event.messages).substring(1);
                String[] args = allcommand.split(" ");
                String label = args[0];
                Main.eventManager.fireEvent(new CommandEvent(sender, label, args, event.group_id));
            }
        }
    }

    private String getWholeText(Message[] msg) {
        String result = "";
        for (Message message : msg) {
            if (message.getType().equals("text")) {
                result += message.getData().getText();
            }
        }
        return result;
    }
}