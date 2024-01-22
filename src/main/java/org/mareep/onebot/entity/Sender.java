package org.mareep.onebot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sender {
    private Integer age;
    private String area;
    private String card;
    private String level;
    private String nickname;
    private String role;
    private String sex;
    private String title;
    private long user_id;
}
