package org.mareep.events;

@Deprecated
public class GroupPokeEvent extends Event{
    public int group_id;
    public int user_id;
    public GroupPokeEvent(int group_id, int user_id) {
        this.group_id = group_id;
        this.user_id = user_id;
    }
}
