package org.mareep.onebot.command;

import org.mareep.onebot.EventListener;
import org.mareep.onebot.QQbot;
import org.mareep.onebot.annotations.EventHandler;
import org.mareep.onebot.entity.Message;
import org.mareep.onebot.entity.Sender;
import org.mareep.onebot.events.CommandEvent;
import org.mareep.onebot.events.GroupMessageEvent;

import java.util.List;

public class CommandListener implements EventListener {
    public List<String> group_id;

    public CommandListener() {


    }

    public CommandListener(List<String> group_id) {

        this.group_id = group_id;
        QQbot.eventManager.registerListener(this);
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
                QQbot.eventManager.fireEvent(new CommandEvent(sender, label, args, event.group_id));
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