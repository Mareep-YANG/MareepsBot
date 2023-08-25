package org.mareep.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private String post_type;
    private String message_type;
    private Long time;
    private String self_id;
    private String sub_type;
    private String message_id;
    private String user_id;
    private String target_id;
    private String message;
    private String raw_message;
    private Integer font;
    private Sender sender;
    private String group_id;
    private Anonymous anonymous;
}
