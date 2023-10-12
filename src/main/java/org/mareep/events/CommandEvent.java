package org.mareep.events;

import org.mareep.entity.Sender;

public class CommandEvent extends Event{
    // 触发指令的事件
    public Sender sender;
    public String label;
    public String[] args;
    public String group_id;






    public CommandEvent(Sender sender , String label, String[] args , String group_id) {
        this.sender = sender;
        this.label = label;
        this.args = args;
        this.group_id = group_id;
    }
}
