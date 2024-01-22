package org.mareep.onebot.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMemberInfo {
    private String group_id;
    private String user_id;
    private String nickname;
    private String card;
    private String sex;
    private int age;
    private String area;
    private String join_time;
    private String last_sent_time;
    private String level;
    private String role;
    private boolean unfriendly;
    private String title;
    private long title_expire_time;
    private boolean card_changeable;
    private long shut_up_timestamp;
}
