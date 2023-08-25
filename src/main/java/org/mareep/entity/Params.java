package org.mareep.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Params {
    private String message_type;
    private String message;
    private long user_id;
    private long group_id;
    private Boolean auto_escape;
    private long duration;
    private String special_title;
}
