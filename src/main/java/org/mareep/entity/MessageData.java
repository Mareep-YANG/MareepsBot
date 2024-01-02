package org.mareep.entity;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class MessageData {
    private String text;
    private String file;
}
